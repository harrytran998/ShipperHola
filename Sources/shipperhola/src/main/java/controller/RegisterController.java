/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import java.sql.Date;
import model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;
import spark.Request;
import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author PC
 */
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
    
    public static final Route VIEW_REGISTER_PAGE = (request, response) -> {
        return getViewManager().renderForRequest(request, "register");
    };

    public static final Route DO_REGISTER = (request, response) -> {
        boolean registerSuccess = false;
        String registerResultMessage = null;
        try {
            Account account = extractParamsAndValidate(request);
            if (getAccountDao().isUsernameExist(account.getUsername())) {
                throw new Exception("Tên người dùng đã tồn tại.");
            }
            registerSuccess = getAccountDao().add(account);
            registerResultMessage = registerSuccess ? "Đăng kí thành công." : "Không thể thêm tài khoản vào database.";
        } catch (DataAccessException ex) {
            LOGGER.error(null, ex);
            registerResultMessage = "Không thể thêm tài khoản vào database.";
        } catch (Exception ex) {
            registerResultMessage = ex.getMessage();
        }
        request.attribute("registerSuccess", registerSuccess);
        request.attribute("registerResultMessage", registerResultMessage);
        return getViewManager().renderForRequest(request, "register");
    };
    
    private static Account extractParamsAndValidate(Request request) throws Exception {
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String fullName = request.queryParams("fullName");
        String genderStr = request.queryParams("gender");
        String dateOfBirthStr = request.queryParams("dateOfBirth");
        String email = request.queryParams("email");
        String phoneNumber = request.queryParams("phoneNumber");
        String address = request.queryParams("address");
        String facebookUrl = request.queryParams("facebookUrl");
        boolean gender = Boolean.parseBoolean(genderStr);
        Date dateOfBirth = StringUtils.isEmpty(dateOfBirthStr) ? null : Date.valueOf(dateOfBirthStr);
        
        request.attribute("username", username);
        request.attribute("fullName", fullName);
        request.attribute("gender", gender);
        request.attribute("dateOfBirth", dateOfBirth);
        request.attribute("email", email);
        request.attribute("phoneNumber", phoneNumber);
        request.attribute("address", address);
        request.attribute("facebookUrl", facebookUrl);
        
        return new Account(
                null,
                username,
                password,
                fullName,
                gender,
                dateOfBirth,
                email,
                phoneNumber,
                address,
                facebookUrl,
                Account.USER_ROLE
        );
    }

    public static void setupRoutes() {
        path("/register", () -> {
            get("", VIEW_REGISTER_PAGE);
            post("", DO_REGISTER);
        });
    }

}
