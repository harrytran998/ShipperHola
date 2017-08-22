package controller;

import java.util.HashMap;
import spark.Route;
import static app.Application.*;
import java.util.Map;
import static spark.Spark.*;

public class StudentController {
    private static final String INDEX_VIEW_NAME = "students/index";
    
    public static final Route VIEW_INDEX_PAGE = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("students", getStudentDao().getAll());
        return getTemplateEngine().render(modelAndView(model, INDEX_VIEW_NAME));
    };
    
    public static void setupRoutes() {
        path("/students", () -> {
            get("", VIEW_INDEX_PAGE);
        });
    }
}
