/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.getTemplateEngine;
import java.sql.Date;
import java.util.HashMap;
import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author PC
 */
public class RegisterController {

    public static final Route VIEW_REGISTER = (request, response) -> {

        return getTemplateEngine().render(modelAndView(new HashMap(), "/create_an_account"));
    };

    public static final Route DO_REGISTER = (request, response) -> {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String fullName = request.queryParams("username");
        boolean gender = Boolean.parseBoolean(request.queryParams("gender"));
        Date dateOfBirth = null;
        try {
            dateOfBirth = Date.valueOf(request.queryParams("dateOfBirth"));
        } catch (IllegalArgumentException ex) {
            return "<h3>Error while adding account to database</h3>";

        }
        String email = request.queryParams("email");
        String phoneNumber = request.queryParams("phoneNumber");
        String address = request.queryParams("address");
        String facebookId = request.queryParams("facebookId");
        String role = request.queryParams("role");
        
        response.redirect("/create_an_account");
        return null; //
    };

    public static void setupRoutes() {
        path("/index", () -> {
            get("", VIEW_REGISTER);
            post("", DO_REGISTER);
        });
    }

}
