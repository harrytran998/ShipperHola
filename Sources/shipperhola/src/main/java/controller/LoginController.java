/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static spark.Spark.*;
import static app.Application.*;
import model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import spark.Route;
import spark.utils.StringUtils;
import util.LoginUtil;

/**
 *
 * @author Quang Hiep
 */
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    public static final Route DO_LOGIN = (request, response) -> {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        try {
            Account account = validateLogin(username, password);
            LoginUtil.setCurrentLogin(request, account.getId());
            return "Login successful.";
        } catch (Exception ex) {
            response.status(400);
            return ex.getMessage();
        }
    };

    private static Account validateLogin(String username, String password) throws Exception {
        if (StringUtils.isEmpty(username)) {
            throw new Exception("Username is empty.");
        }
        if (StringUtils.isEmpty(password)) {
            throw new Exception("Password is empty.");
        }
        Account account = null;
        try {
            account = getAccountDao().getByUsername(username);
        } catch (DataAccessException ex) {
            LOGGER.error(null, ex);
            throw new Exception("Error while querying from the database.");
        }
        if (account == null) {
            throw new Exception("Username doesn't exist.");
        }
        if (!account.getPassword().equals(password)) {
            throw new Exception("Incorrect password.");
        }
        return account;
    }

    public static void setupRoutes() {
        post("/login", DO_LOGIN);
    }

}
