package controller;

import spark.Route;
import static app.Application.*;
import static spark.Spark.*;
import static app.ApplicationConstants.*;

public class IndexController {
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        return getViewManager().renderForRequest(request, INDEX_VIEW_NAME);
    };
    
    public static void setupRoutes() {
        get("/", VIEW_INDEX_PAGE);
    }
}
