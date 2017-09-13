/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import spark.Route;
import static spark.Spark.*;
import util.view.IViewManager;
import util.view.ThymeleafViewManager;
/**
 *
 * @author Le Cao Nguyen
 */
public class TestController {
    private static final IViewManager viewManager = new ThymeleafViewManager("/templates_full/", ".html", false);
    
    public static final Route VIEW_TEST_TEMPLATE_PAGE = (request, response) -> {
        return viewManager.renderForRequest(request, "template");
    };
    
    public static final Route VIEW_TEST_INDEX_PAGE = (request, response) -> {
        return viewManager.renderForRequest(request, "index");
    };
    
    public static void setupRoutes() {
        path("/test", () -> {
            get("", VIEW_TEST_INDEX_PAGE);
            get("/template", VIEW_TEST_TEMPLATE_PAGE);
        });
    }
}
