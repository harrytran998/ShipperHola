package controller;

import spark.Route;
import static app.Application.*;
import static spark.Spark.*;
import static app.ApplicationConstants.*;
import java.util.List;
import model.Category;

public class IndexController {
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        List<Category> categories = getCategoryDao().getAll();
        request.attribute("categories", categories);
        return getViewManager().renderForRequest(request, INDEX_VIEW_NAME);
    };
    
    public static void setupRoutes() {
        get("/", VIEW_INDEX_PAGE);
    }
}
