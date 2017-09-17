package controller;

import spark.Route;
import static app.Application.*;
import java.util.List;
import model.Product;
import static spark.Spark.*;

public class IndexController {
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        List<Product> latestProducts = getProductDao().search(null, null, null, null, null, null, false, "date", false, null, 5);
        request.attribute("latestProducts", latestProducts);
        return getViewManager().renderForRequest(request, "index");
    };
    
    public static void setupRoutes() {
        get("/", VIEW_INDEX_PAGE);
    }
}
