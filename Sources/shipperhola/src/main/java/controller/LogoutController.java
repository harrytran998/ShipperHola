/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author Le Cao Nguyen
 */
public class LogoutController {

    public static final Route DO_LOGOUT = (request, response) -> {
        request.session().invalidate();
        response.redirect("/");
        return null;
    };

    public static void setupRoutes() {
        path("/logout", () -> {
            get("", DO_LOGOUT);
            post("", DO_LOGOUT);
        });
    }
}
