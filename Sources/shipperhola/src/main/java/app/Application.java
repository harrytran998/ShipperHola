/*
 * Copyright © 2017 XVideos Team
 */
package app;

import controller.IndexController;
import controller.LoginController;
import controller.ProductSearchController;
import controller.RegisterController;
import dao.AccountDao;
import dao.ProductDao;
import java.io.IOException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import util.view.IViewManager;
import util.view.ThymeleafViewManager;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    public static final boolean IS_RUNNING_ON_LOCALHOST = true;
    public static final String CONFIGURATION_FILE_NAME = "application.properties";
    public static final String DEFAULT_CONFIGURATION_FILE_NAME = "application.default.properties";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Configuration & dependencies">
    private static ApplicationConfiguration configuration;
    private static TemplateEngine templateEngine;
    private static IViewManager viewManager;
    private static DataSource dataSource;
    private static AccountDao accountDao;
    private static ProductDao productDao;
    

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters for dependencies">
    public static ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    public static TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public static IViewManager getViewManager() {
        return viewManager;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static ProductDao getProductDao() {
        return productDao;
    }
    public static AccountDao getAccountDao() {
        return accountDao;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setup methods">
    /**
     * Load the application's configuration from file. If configuration file
     * does
     *
     * @throws IOException
     */
    private static void loadConfiguration() throws IOException {
        try {
            configuration = ApplicationConfiguration.fromFile(CONFIGURATION_FILE_NAME);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            configuration = ApplicationConfiguration.fromFile(DEFAULT_CONFIGURATION_FILE_NAME);
        }
    }

    /**
     * Initialize application's dependencies.
     */
    private static void initializeDependencies() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine = new ThymeleafTemplateEngine(templateResolver);
        viewManager = new ThymeleafViewManager();
        dataSource = new DriverManagerDataSource(configuration.getDataSourceUrl(), configuration.getDataSourceUser(), configuration.getDataSourcePassword());
        accountDao = new AccountDao(dataSource);
    }

    /**
     * Configure Spark server.
     */
    private static void configureServer() {
        if (IS_RUNNING_ON_LOCALHOST) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }
    }

    /**
     * Setup routes from all controllers.
     */
    private static void setupRoutes() {
        IndexController.setupRoutes();
        LoginController.setupRoutes();
        RegisterController.setupRoutes();
        ProductSearchController.setupRoutes();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Main methods">
    public static void main(String[] args) {
        try {
            loadConfiguration();
            initializeDependencies();
            configureServer();
            setupRoutes();
        } catch (Exception ex) {
            LOGGER.error(null, ex);
        }
    }
    // </editor-fold>

}
