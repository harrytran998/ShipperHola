/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import filter.PrepareDataFilters;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Order;
import model.Product;
import model.ProductComment;
import model.ProductPicture;
import model.ProductReview;
import model.ShippingAddress;
import org.apache.commons.lang3.time.DateUtils;
import spark.Request;
import spark.Route;
import static spark.Spark.*;
import spark.utils.StringUtils;

/**
 *
 * @author nhattq
 */
public class ProductController {

    public static final Route VIEW_SEARCH_PAGE = (request, response) -> {
        try {
            extractParamsAndValidate(request);
            List<Product> products = search(
                    request.<String>attribute("keyword"),
                    request.<Double>attribute("minPrice"),
                    request.<Double>attribute("maxPrice"),
                    request.<Date>attribute("minDate"),
                    request.<Date>attribute("maxDate"),
                    request.<Integer>attribute("categoryId"),
                    request.<Boolean>attribute("onlyAllowOrder"),
                    request.<String>attribute("orderColumn"),
                    request.<Boolean>attribute("ascending"),
                    request.<Integer>attribute("offsetRecords"),
                    request.<Integer>attribute("fetchRecords")
            );
            request.attribute("products", products);
            return getViewManager().renderForRequest(request, "products/search");
        } catch (NumberFormatException | ParseException ex) {
            response.status(400);
            return ex.getMessage();
        }
    };

    private static void extractParamsAndValidate(Request request) throws NumberFormatException, ParseException {
        String keyword = request.queryParams("keyword");
        String minPriceStr = request.queryParams("minPrice");
        String maxPriceStr = request.queryParams("maxPrice");
        String minDateStr = request.queryParams("minDate");
        String maxDateStr = request.queryParams("maxDate");
        String categoryIdStr = request.queryParams("categoryId");
        String onlyAllowOrderStr = request.queryParams("onlyAllowOrder");
        String orderBy = request.queryParams("orderBy");
        String offsetRecordsStr = request.queryParams("offsetRecords");
        String fetchRecordsStr = request.queryParams("fetchRecords");

        keyword = StringUtils.isEmpty(keyword) ? null : keyword.trim().toLowerCase();
        Double minPrice = StringUtils.isEmpty(minPriceStr) ? null : Double.parseDouble(minPriceStr);
        Double maxPrice = StringUtils.isEmpty(maxPriceStr) ? null : Double.parseDouble(maxPriceStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date minDate = StringUtils.isEmpty(minDateStr) ? null : new Date(sdf.parse(minDateStr).getTime());
        Date maxDate = StringUtils.isEmpty(maxDateStr) ? null : new Date(sdf.parse(maxDateStr).getTime());
        Integer categoryId = StringUtils.isEmpty(categoryIdStr) ? null : Integer.parseInt(categoryIdStr);
        boolean onlyAllowOrder = onlyAllowOrderStr != null;
        String orderColumn = "date"; //defaut
        boolean ascending = false; //defaut
        if (!StringUtils.isEmpty(orderBy)) {
            String[] split = orderBy.split("_");
            if (split.length == 2 && !StringUtils.isEmpty(split[0]) && split[1].toUpperCase().matches("(ASC|DESC)")) {
                orderColumn = split[0];
                ascending = split[1].toUpperCase().equals("ASC");
            }
        }
        Integer offsetRecords = StringUtils.isEmpty(offsetRecordsStr) ? null : Integer.parseInt(offsetRecordsStr);
        Integer fetchRecords = StringUtils.isEmpty(fetchRecordsStr) ? null : Integer.parseInt(fetchRecordsStr);

        request.attribute("keyword", keyword);
        request.attribute("minPrice", minPrice);
        request.attribute("maxPrice", maxPrice);
        request.attribute("minDate", minDate);
        request.attribute("maxDate", maxDate);
        request.attribute("categoryId", categoryId);
        request.attribute("onlyAllowOrder", onlyAllowOrder);
        request.attribute("orderBy", orderBy);
        request.attribute("orderColumn", orderColumn);
        request.attribute("ascending", ascending);
        request.attribute("offsetRecords", offsetRecords);
        request.attribute("fetchRecords", fetchRecords);
    }

    private static void fetchForeignProperties(Product product) {
        List<Order> orders = getOrderDao().getByProduct(product.getId());
        List<ProductComment> comments = getProductCommentDao().getByProduct(product.getId());
        List<ProductPicture> pictures = getProductPictureDao().getByProduct(product.getId());
        List<ProductReview> reviews = getProductReviewDao().getByProduct(product.getId());
        List<ShippingAddress> shippingAddresses = getShippingAddressDao().getByProduct(product.getId());

        orders.forEach(order -> order.setProduct(product));
        comments.forEach(comment -> comment.setProduct(product));
        pictures.forEach(picture -> picture.setProduct(product));
        reviews.forEach(review -> review.setProduct(product));
        
        product.setOrders(orders);
        product.setComments(comments);
        product.setPictures(pictures);
        product.setReviews(reviews);
        product.setShippingAddresses(shippingAddresses);
    }

    private static List<Product> search(String keyword, Double minPrice, Double maxPrice, Date minDate, Date maxDate, Integer categoryId, boolean onlyAllowOrder, String orderColumn, boolean ascending, Integer offsetRecords, Integer fetchRecords) {
        List<Product> products = getProductDao().getAll();
        products.forEach(ProductController::fetchForeignProperties);
        Stream<Product> productStream = products.stream();
        
        if (keyword != null) {
            productStream = productStream.filter(product -> product.getName().toLowerCase().contains(keyword));
        }
        if (minPrice != null) {
            productStream = productStream.filter(product -> product.getCurrentPrice() >= minPrice);
        }
        if (maxPrice != null) {
            productStream = productStream.filter(product -> product.getCurrentPrice() <= minPrice);
        }
        if (minDate != null) {
            productStream = productStream.filter(product -> DateUtils.truncatedCompareTo(product.getDate(), minDate, Calendar.DAY_OF_MONTH) >= 0);
        }
        if (maxDate != null) {
            productStream = productStream.filter(product -> DateUtils.truncatedCompareTo(product.getDate(), maxDate, Calendar.DAY_OF_MONTH) <= 0);
        }
        if (categoryId != null) {
            productStream = productStream.filter(product -> Objects.equals(product.getCategory().getId(), categoryId));
        }
        if (onlyAllowOrder) {
            productStream = productStream.filter(product -> product.isAllowOrder());
        }
        if (orderColumn != null) {
            switch(orderColumn) {
                case "date":
                    productStream = productStream.sorted((p1, p2) -> p1.getDate().compareTo(p2.getDate()) * (ascending ? 1 : -1));
                    break;
                case "currentPrice":
                    productStream = productStream.sorted((p1, p2) -> Double.compare(p1.getCurrentPrice(), p2.getCurrentPrice()) * (ascending ? 1 : -1));
                    break;
                case "name":
                    productStream = productStream.sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()) * (ascending ? 1 : -1));
                    break;
                case "averageRating":
                    productStream = productStream.sorted((p1, p2) -> Double.compare(p1.getAverageRating(), p2.getAverageRating()) * (ascending ? 1 : -1));
                    break;
                case "orderQuantityToday":
                    productStream = productStream.sorted((p1, p2) -> Integer.compare(p1.getOrderQuantityToday(), p2.getOrderQuantityToday()) * (ascending ? 1 : -1));
                    break;
                default:
                    productStream = productStream.sorted((p1, p2) -> -p1.getDate().compareTo(p2.getDate()));
            }
        }
        if (offsetRecords != null) {
            productStream = productStream.skip(offsetRecords);
        }
        if (fetchRecords != null) {
            productStream = productStream.limit(fetchRecords);
        }
        return productStream.collect(Collectors.toList());
    }

    public static void setupRoutes() {
        path("/products", () -> {
            before(PrepareDataFilters.EMBED_COMMON_DATA_INTO_REQUEST);
            get("/search", VIEW_SEARCH_PAGE);
        });
    }

}
