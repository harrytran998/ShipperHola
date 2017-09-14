/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import filter.PrepareDataFilters;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Product;
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
            List<Product> products = getProductDao().search(
                    request.<String>attribute("keyword"), 
                    request.<Double>attribute("minPrice"),
                    request.<Double>attribute("maxPrice"),
                    request.<Date>attribute("minDate"),
                    request.<Date>attribute("maxDate"),
                    request.<Integer>attribute("categoryId"),
                    null,
                    true,
                    null, 
                    null
            );
            request.attribute("products", products);
            return getViewManager().renderForRequest(request, "products/search");
        } catch (Exception ex) {
            response.status(400);
            return ex.getMessage();
        }
    };
    
    private static void extractParamsAndValidate(Request request) throws Exception {
        String keyword = request.queryParams("keyword");
        String minPriceStr = request.queryParams("minPrice");
        String maxPriceStr = request.queryParams("maxPrice");
        String minDateStr = request.queryParams("minDate");
        String maxDateStr = request.queryParams("maxDate");
        String categoryIdStr = request.queryParams("categoryId");
        keyword = StringUtils.isEmpty(keyword) ? null : keyword;
        Double minPrice = StringUtils.isEmpty(minPriceStr) ? null : Double.parseDouble(minPriceStr);
        Double maxPrice = StringUtils.isEmpty(maxPriceStr) ? null : Double.parseDouble(maxPriceStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date minDate = StringUtils.isEmpty(minDateStr) ? null : new Date(sdf.parse(minDateStr).getTime());
        Date maxDate = StringUtils.isEmpty(maxDateStr) ? null : new Date(sdf.parse(maxDateStr).getTime());
        Integer categoryId = StringUtils.isEmpty(categoryIdStr) ? null : Integer.parseInt(categoryIdStr);
        request.attribute("keyword", keyword);
        request.attribute("minPrice", minPrice);
        request.attribute("maxPrice", maxPrice);
        request.attribute("minDate", minDate);
        request.attribute("maxDate", maxDate);
        request.attribute("categoryId", categoryId);
    }
    
    public static void setupRoutes() {
        path("/products", () -> {
            before(PrepareDataFilters.EMBED_CATEGORIES_TO_REQUEST);
            get("/search", VIEW_SEARCH_PAGE);
        });
    }
    
}
