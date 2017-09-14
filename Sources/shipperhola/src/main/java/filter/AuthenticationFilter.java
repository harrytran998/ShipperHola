/*
 * Copyright © 2017 XVideos Team
 */
package filter;

import static app.Application.*;
import static spark.Spark.*;
import model.Account;
import spark.Filter;
import util.LoginUtil;

/**
 *
 * @author Le Cao Nguyen
 */
public class AuthenticationFilter {
    private static final String CURRENT_LOGIN_ACCOUNT_ATTRIBUTE = "currentLoginAccount";
    private static final String NOT_LOGGED_IN_ERROR_MESSAGE = "<h1>You must be logged in to access this page.</h1>";
    
    public static final Filter UPDATE_CURRENT_LOGIN_ACCOUNT_TO_REQUEST_MODEL = (request, response) -> {
        Integer accountId = LoginUtil.getCurrentLogin(request);
        Account currentLoginAccount = accountId == null ? null : getAccountDao().getById(accountId);
        request.attribute(CURRENT_LOGIN_ACCOUNT_ATTRIBUTE, currentLoginAccount);
    };
    
    public static final Filter REQUIRE_LOGIN = (request, response) -> {
        if (LoginUtil.getCurrentLogin(request) == null) {
            halt(403, NOT_LOGGED_IN_ERROR_MESSAGE);
        }
    };
    
    public static void setupFilters() {
        before(UPDATE_CURRENT_LOGIN_ACCOUNT_TO_REQUEST_MODEL);
    }
}
