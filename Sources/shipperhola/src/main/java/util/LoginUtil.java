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
     public static String getCurrentLogin(Request request) {
        return request.session().<String>attribute("currentLogin");
    }

    public static void setCurrentLogin(Request request, String username) {
        request.session().attribute("currentLogin", username);
    }
}
