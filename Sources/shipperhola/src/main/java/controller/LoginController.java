/*
 * Copyright © 2017 XVideos Team
 */
package controller;

/**
 *
 * @author PC
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static spark.Spark.*;
import static app.Application.*;
import java.sql.SQLException;
import java.util.HashMap;
import model.Account;
import spark.Route;
import static util.LoginUtil.setCurrentLogin;

/**
 *
 * @author Quang Hiep
 */
public class LoginController {

    public static final Route VIEW_LOGIN_PAGE = (request, response) -> {
        return getTemplateEngine().render(modelAndView(new HashMap(), "/index"));
    };

    public static final Route DO_LOGIN = (request, response) -> {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        try {
            validateLogin(username, password);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        setCurrentLogin(request, username);
      
        return null;
    };

    private static void validateLogin(String username, String password) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new Exception("No username provided.");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("No password provided.");
        }
      Account account;
        try {
      account =  getAccount().getByUsername(username);
        } catch (SQLException ex) {
            throw new Exception("Error while querying from the database", ex);
        }
        if (account == null) {
            throw new Exception("Username doesn't exist.");
        }
        if (!account.getPassword().equals(password)) {
            throw new Exception("Incorrect password.");
        }
   
    }

   

    public static void setupRoutes() {
        path("/index", () -> {
            get("", VIEW_LOGIN_PAGE);
            post("", DO_LOGIN);
        });

    }

}
