/*
 * Copyright © 2017 XVideos Team
 */
package app;

import controller.IndexController;
import controller.LoginController;
import controller.LogoutController;
import controller.ProductController;
import controller.RegisterController;
import controller.TestController;
import dao.AccountDao;
import dao.CartItemDao;
import dao.CategoryDao;
import dao.OrderDao;
import dao.OrderMessageDao;
import dao.ProductCommentDao;
import dao.ProductDao;
import dao.ProductReviewDao;
import dao.SearchKeywordDao;
import dao.ShippingAddressDao;
import filter.PrepareDataFilters;
import java.io.IOException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static spark.Spark.*;
import util.view.IViewManager;
import util.view.ThymeleafViewManager;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final String DEFAULT_CONFIGURATION_FILE_NAME = "application.default.properties";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Configuration & dependencies">
    private static ApplicationConfiguration configuration;
    private static IViewManager viewManager;
    private static DataSource dataSource;
    private static AccountDao accountDao;
    private static CartItemDao cartItemDao;
    private static CategoryDao categoryDao;
    private static OrderDao orderDao;
    private static OrderMessageDao orderMessageDao;
    private static ProductDao productDao;
    private static ProductCommentDao productCommentDao;
    private static ProductReviewDao productReviewDao;
    private static SearchKeywordDao searchKeywordDao;
    private static ShippingAddressDao shippingAddressDao;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters for dependencies">
    public static ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    public static IViewManager getViewManager() {
        return viewManager;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static AccountDao getAccountDao() {
        return accountDao;
    }

    public static CartItemDao getCartItemDao() {
        return cartItemDao;
    }

    public static CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public static OrderDao getOrderDao() {
        return orderDao;
    }

    public static OrderMessageDao getOrderMessageDao() {
        return orderMessageDao;
    }

    public static ProductDao getProductDao() {
        return productDao;
    }

    public static ProductCommentDao getProductCommentDao() {
        return productCommentDao;
    }

    public static ProductReviewDao getProductReviewDao() {
        return productReviewDao;
    }

    public static SearchKeywordDao getSearchKeywordDao() {
        return searchKeywordDao;
    }

    public static ShippingAddressDao getShippingAddressDao() {
        return shippingAddressDao;
    }    

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setup methods">
    /**
     * Load the application's configuration by looking at the startup arguments.
     * If the configuration file is not specified, use the default configuration
     * file in resource directory.
     *
     * @param args The startup arguments
     * @throws IOException If there is error during loading the configuration
     * file.
     */
    private static void loadConfiguration(String[] args) throws IOException {
        String configurationFileName = DEFAULT_CONFIGURATION_FILE_NAME;
        if (args != null && args.length > 0) {
            configurationFileName = args[0];
            LOGGER.info("Using configuration file: {}.", configurationFileName);
        } else {
            LOGGER.info("Using default configuration file.");
        }
        configuration = ApplicationConfiguration.fromFile(configurationFileName);
    }

    /**
     * Initialize application's dependencies.
     */
    private static void initializeDependencies() {
        viewManager = new ThymeleafViewManager(null, null, configuration.isThymeleafCacheable());
        dataSource = new DriverManagerDataSource(configuration.getDataSourceUrl(), configuration.getDataSourceUser(), configuration.getDataSourcePassword());
        accountDao = new AccountDao(dataSource);
        cartItemDao = new CartItemDao(dataSource);
        categoryDao = new CategoryDao(dataSource);
        orderDao = new OrderDao(dataSource);
        orderMessageDao = new OrderMessageDao(dataSource);
        productDao = new ProductDao(dataSource);
        productCommentDao = new ProductCommentDao(dataSource);
        productReviewDao = new ProductReviewDao(dataSource);
        searchKeywordDao = new SearchKeywordDao(dataSource);
        shippingAddressDao = new ShippingAddressDao(dataSource);
    }

    /**
     * Configure Spark server.
     */
    private static void configureServer() {
        // Set static file location
        if (configuration.isDevelopmentMode()) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }
        // Get Heroku assigned port
        Integer serverPort;
        try {
            serverPort = Integer.parseInt(new ProcessBuilder().environment().get("PORT"));
        } catch (NumberFormatException ex) {
            serverPort = configuration.getServerPort();
        }
        port(serverPort);
    }

    /**
     * Setup routes from all controllers.
     */
    private static void setupRoutesAndFilters() {
        PrepareDataFilters.setupFilters();
        TestController.setupRoutes();
        IndexController.setupRoutes();
        LoginController.setupRoutes();
        LogoutController.setupRoutes();
        RegisterController.setupRoutes();
        ProductController.setupRoutes();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Main methods">
    /**
     * Start the application.<br/>
     * The configuration file can be specified as the first argument. For
     * example:<br/>
     * java -jar application.jar application.properties
     *
     * @param args The startup arguments, which may contains the path to
     * configuration file.
     */
    public static void main(String[] args) {
        try {
            loadConfiguration(args);
            initializeDependencies();
            configureServer();
            setupRoutesAndFilters();
        } catch (Exception ex) {
            LOGGER.error(null, ex);
        }
    }
    // </editor-fold>

}
