/*
 * Copyright © 2017 XVideos Team
 */
package filter;

import static spark.Spark.*;
import spark.Filter;
import util.LoginUtil;

/**
 *
 * @author Le Cao Nguyen
 */
public class AuthenticationFilter {

    private static final String NOT_LOGGED_IN_ERROR_MESSAGE = "<h1>You must be logged in to access this page.</h1>";

    public static final Filter REQUIRE_LOGIN = (request, response) -> {
        if (LoginUtil.getCurrentLogin(request) == null) {
            halt(403, NOT_LOGGED_IN_ERROR_MESSAGE);
        }
    };
}
