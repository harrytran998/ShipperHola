/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import filter.AuthenticationFilter;
import java.util.List;
import model.CartItem;
import spark.Route;
import static spark.Spark.*;
import util.LoginUtil;

/**
 *
 * @author nhattq
 */
public class CartController {

    public static final Route VIEW_CART_PAGE = (request, response) -> {
        Integer accountId = LoginUtil.getCurrentLogin(request);
        if (accountId != null) {
            List<CartItem> cartItems = getCartItemDao().getByAccount(accountId);
            request.attribute("cartItems", cartItems);
        }
        return getViewManager().renderForRequest(request, "cart/index");
    };

    public static void setupRoutes() {
        path("/cart", () -> {
            before(AuthenticationFilter.REQUIRE_LOGIN);
            get("", VIEW_CART_PAGE);
        });
    }

}
