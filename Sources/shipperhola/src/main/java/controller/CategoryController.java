/*
 * Copyright © 2017 XVideos Team
 */
package controller;
import static spark.Spark.*;
import static app.App.*;
import java.util.HashMap;
import java.util.Map;
import spark.Route;

/**
 *
 * @author Admin
 */
public class CategoryController {
    private static final String INDEX_VIEW_CATEGORY = "category/index";
    private static final Route VIEW_INDEX_PAGE = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("category", getCategoryDao().getAll());
        return getTemplateEngine().render(modelAndView(model, INDEX_VIEW_CATEGORY));
    };
    
    public static void setupRoutes(){
        path("/category", () -> {
            get("", VIEW_INDEX_PAGE);
        });
    }
}
