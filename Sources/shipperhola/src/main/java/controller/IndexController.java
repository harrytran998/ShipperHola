package controller;

import java.util.HashMap;
import spark.Route;
import static app.Application.*;
import java.util.Map;
import static spark.Spark.*;

public class IndexController {
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        final String message = "ShipperHola by XVideos Team.";
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return getTemplateEngine().render(modelAndView(model, "index"));
    };
    
    public static void setupRoutes() {
        get("/", VIEW_INDEX_PAGE);
    }
}
