/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import filter.PrepareDataFilters;
import java.sql.Date;
import java.text.ParseException;
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
                    request.<String>attribute("orderColum"),
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
        String orderBy = request.queryParams("orderBy");
        String offsetRecordsStr = request.queryParams("offsetRecords");
        String fetchRecordsStr = request.queryParams("fetchRecords");
        
        keyword = StringUtils.isEmpty(keyword) ? null : keyword.trim();
        Double minPrice = StringUtils.isEmpty(minPriceStr) ? null : Double.parseDouble(minPriceStr);
        Double maxPrice = StringUtils.isEmpty(maxPriceStr) ? null : Double.parseDouble(maxPriceStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date minDate = StringUtils.isEmpty(minDateStr) ? null : new Date(sdf.parse(minDateStr).getTime());
        Date maxDate = StringUtils.isEmpty(maxDateStr) ? null : new Date(sdf.parse(maxDateStr).getTime());
        Integer categoryId = StringUtils.isEmpty(categoryIdStr) ? null : Integer.parseInt(categoryIdStr);
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
        request.attribute("orderColumn", orderColumn);
        request.attribute("ascending", ascending);
        request.attribute("offsetRecords", offsetRecords);
        request.attribute("fetchRecords", fetchRecords);
    }
    
    public static void setupRoutes() {
        path("/products", () -> {
            before(PrepareDataFilters.EMBED_COMMON_DATA_INTO_REQUEST);
            get("/search", VIEW_SEARCH_PAGE);
        });
    }
    
}
