/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Product;
import spark.Request;
import spark.Route;
import static spark.Spark.*;
import static spark.Spark.modelAndView;

/**
 *
 * @author nhattq
 */
public class ProductSearchController {
    
    public static final Route VIEW_SEARCH_PAGE = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        return getTemplateEngine().render(modelAndView(model, "search"));
    };
    
    
    public static final Route DO_SEARCH = (request, response) -> {
        try {
            Map<String, Object> params = extractAndValidate(request);
            List<Product> products = getProductDao().search(
                    (String) params.get("keyword"), 
                    (Double) params.get("minPrice"),
                    (Double) params.get("maxPrice"),
                    null,
                    true,
                    null, 
                    null
            );
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return null;
    };
    
    private static Map<String, Object> extractAndValidate(Request request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String minPriceStr = request.queryParams("minPrice");
        String maxPriceStr = request.queryParams("maxPrice");
        String minDateStr = request.queryParams("minDate");
        String maxDateStr = request.queryParams("maxDate");
        
        Date minDate, maxDate;
        Double maxPrice, minPrice;
        try {
            minPrice = Double.parseDouble(minPriceStr);
            maxPrice = Double.parseDouble(maxPriceStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            minDate = sdf.parse(minPriceStr);
            maxDate = sdf.parse(maxDateStr);
        } catch (NumberFormatException ex) {
            throw new Exception(ex);
        }
        if (minPrice < 0 || maxPrice <0) {
            throw new Exception("Must be >= 0");
        }
        if (minPrice > maxPrice) {
            throw new Exception("Min Price can't be greater than Max Price");
        }
        if (minDate.compareTo(maxDate) > 0) {
            throw new Exception("Mindate can't be greater than MaxDate");
        }
        Calendar calendar = Calendar.getInstance();
        if (maxDate.compareTo(calendar.getTime()) > 0) {
            throw new Exception("Mindate can't be greater than Current time");
        }
        params.put("minPrice", minPrice);
        params.put("maxPrice", maxPrice);
        params.put("minDate", minDate);
        params.put("maxDate", maxDate);
        return params;
    }
    
    public static void setupRoutes() {
        path("/search", () -> {
            get("", VIEW_SEARCH_PAGE);
            post("", DO_SEARCH);
        });
    }
    
}
