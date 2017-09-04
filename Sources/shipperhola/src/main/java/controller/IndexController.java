package controller;

import java.util.HashMap;
import spark.Route;
import static app.Application.*;
import java.util.Map;
import static spark.Spark.*;
import static app.ApplicationConstants.*;

public class IndexController {
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        return getTemplateEngine().render(modelAndView(model, "index"));
    };
    
    public static void setupRoutes() {
        get("/", VIEW_INDEX_PAGE);
    }
}
