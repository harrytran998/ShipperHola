/*
 * Copyright © 2017 XVideos Team
 */
package app;

import controller.IndexController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Application {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    public static final boolean IS_RUNNING_ON_LOCALHOST = true;
    public static final String CONFIGURATION_FILE_NAME = "application.properties";
    public static final String DEFAULT_CONFIGURATION_FILE_NAME = "application.default.properties";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Configuration & dependencies">
    private static ApplicationConfiguration configuration;
    private static TemplateEngine templateEngine;
    private static DataSource dataSource;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters for dependencies">
    public static ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    public static TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public static DataSource getDataSource() {
        return dataSource;
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
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            configuration = ApplicationConfiguration.fromFile(DEFAULT_CONFIGURATION_FILE_NAME);
        }
    }

    /**
     * Initialize application's dependencies.
     */
    private static void initializeDependencies() {
        templateEngine = new ThymeleafTemplateEngine();
        dataSource = new DriverManagerDataSource(configuration.getDataSourceUrl(), configuration.getDataSourceUser(), configuration.getDataSourcePassword());

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
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

}
