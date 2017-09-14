/*
 * Copyright © 2017 XVideos Team
 */
package util;

import spark.Request;

/**
 *
 * @author PC
 */
public class LoginUtil {
    public static final String CURRENT_LOGIN_ATTRIBUTE = "currentLogin";
    
    public static Integer getCurrentLogin(Request request) {
        return request.session().<Integer>attribute(CURRENT_LOGIN_ATTRIBUTE);
    }

    public static void setCurrentLogin(Request request, Integer accountId) {
        request.session().attribute(CURRENT_LOGIN_ATTRIBUTE, accountId);
    }
}
