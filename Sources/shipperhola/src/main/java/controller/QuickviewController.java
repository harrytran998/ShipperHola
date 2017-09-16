/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.getProductDao;
import static app.Application.getViewManager;
import java.util.List;
import model.Product;
import spark.Route;
import static spark.Spark.*;


/**
 *
 * @author Quang Hiep
 */
public class QuickviewController {

    private static final Route QUICK_VIEW = ((request, response) -> {
        try {
            List<Product> products = getProductDao().getAll();
            request.attribute("products", products);
            return getViewManager().renderForRequest(request, "products/quickview");
        } catch (Exception ex) {
            response.status(400);
            return ex.getMessage();
        }
    });
    
    public static void setupRoutes() {
        post("/quickview", QUICK_VIEW);
    }
}
