package controller;

import spark.Route;
import static app.Application.*;
import filter.PrepareDataFilters;
import static spark.Spark.*;

public class IndexController {
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        return getViewManager().renderForRequest(request, "index");
    };
    
    public static void setupRoutes() {
        path("/", () -> {
            before(PrepareDataFilters.EMBED_CATEGORIES_TO_REQUEST);
            get("", VIEW_INDEX_PAGE);
        });
    }
}
