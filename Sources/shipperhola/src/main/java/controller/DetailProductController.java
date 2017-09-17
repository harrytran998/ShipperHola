/*
 * Copyright © 2017 XVideos Team
 */
package controller;

import static app.Application.*;
import spark.Request;
import spark.Route;
import spark.utils.StringUtils;

public class DetailProductController {

    public static final Route VIEW_DETAIL_PRODUCT_PAGE = (request, response) -> {
        return getViewManager().renderForRequest(request, "products/detail");
    };

    private static void extractParamsAndValidate(Request request) {
        String productPictureIdStr = request.queryParams("pictureProductId");
        String currentPriceStr = request.queryParams("currentPrice");
        String allowOrderStr = request.queryParams("allowOrder");
        String description = request.queryParams("description");
        String shippingAddressIdStr = request.queryParams("shippingAddressId");

        Integer productPictureId = StringUtils.isEmpty(productPictureIdStr) ? null : Integer.parseInt(productPictureIdStr);
        Double currentPrice = StringUtils.isEmpty(currentPriceStr) ? null : Double.parseDouble(currentPriceStr);
        Boolean allowOrder = StringUtils.isEmpty(allowOrderStr) ? null : Boolean.parseBoolean(allowOrderStr);
        Integer shippingAddressId = StringUtils.isEmpty(shippingAddressIdStr) ? null : Integer.parseInt(shippingAddressIdStr);

        request.attribute("productPictureId", productPictureId);
        request.attribute("currentPrice", currentPrice);
        request.attribute("allowOrder", allowOrder);
        request.attribute("description", description);
        request.attribute("shippingAddressId", shippingAddressId);

    }

}
