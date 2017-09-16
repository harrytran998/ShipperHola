/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import filter.PrepareDataFilters;
import java.util.List;
import model.CartItem;
import spark.Request;
import spark.Route;
import spark.Spark;
import static spark.Spark.*;
import spark.utils.StringUtils;

/**
 *
 * @author nhattq
 */
public class CartItemController {

    public static final Route VIEW_CARTITEM_PAGE = (request, response) -> {
        try {
            extractParamsAndValidate(request);
            List<CartItem > cartItems = getCartItemDao().getByAccount(request.<Integer>attribute("accountId"));
            
            return getViewManager().renderForRequest(request, "products/cartItem");
        } catch (Exception ex) {
            response.status(400);
            return ex.getMessage();
        }
    };

    private static void extractParamsAndValidate(Request request) throws Exception {
        String accountIdStr = request.queryParams("accountId");
        String productIdStr = request.queryParams("productId");
        String quantityStr = request.queryParams("quantity");
        String note = request.queryParams("note");

        Integer accountId = StringUtils.isEmpty(accountIdStr) ? null : Integer.parseInt(accountIdStr);
        Integer productId = StringUtils.isEmpty(productIdStr) ? null : Integer.parseInt(productIdStr);
    }

    public static void setupRoutes() {
        Spark.path("/products", () -> {
            Spark.before(PrepareDataFilters.EMBED_CARTITEM_TO_REQUEST);
            get("/cartItem", VIEW_CARTITEM_PAGE);
        });
    }

}
