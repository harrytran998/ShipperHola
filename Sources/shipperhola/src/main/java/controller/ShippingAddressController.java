/*
 * Copyright © 2017 XVideos Team
 */
package controller;
import static spark.Spark.*;
import static app.Application.*;
import java.util.HashMap;
import java.util.Map;
import spark.Route;

/**
 *
 * @author Admin
 */
public class ShippingAddressController {
    private static final String INDEX_VIEW_SHIPPING_ADDRESS = "shippingaddress/index";
    private static final Route VIEW_INDEX_PAGE = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("shippingaddress", getAddressDao().getAll());
        return getTemplateEngine().render(modelAndView(model, INDEX_VIEW_SHIPPING_ADDRESS));
    };
    
    public static void setupRoutes(){
        path("/category", () -> {
            get("", VIEW_INDEX_PAGE);
        });
    }
}
