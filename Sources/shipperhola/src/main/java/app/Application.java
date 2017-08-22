package app;

import controller.IndexController;
import controller.StudentController;
import dao.StudentDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import static spark.Spark.*;

public class Application {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final boolean IS_RUNNING_ON_LOCALHOST = true;
    private static final String CONFIGURATION_FILE_NAME = "application.properties";
    private static final String DEFAULT_CONFIGURATION_FILE_NAME = "application.default.properties";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Configuration & dependencies">
    private static ApplicationConfiguration configuration;
    private static TemplateEngine templateEngine;
    private static DataSource dataSource;
    private static StudentDao studentDao;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters for dependencies">
    /**
     * Get the application's configuration object.
     * @return The configuration object.
     */
    public static ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Get the application's template engine to use with Spark.
     * @return The template engine.
     */
    public static TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    /**
     * Get the application's data source for use in DAO classes.
     * @return The data source.
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Get the student DAO object.
     * @return The student DAO.
     */
    public static StudentDao getStudentDao() {
        return studentDao;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Main methods">
    /**
     * Load the application's configuration from file. If configuration file does
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
        studentDao = new StudentDao(dataSource);
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
        StudentController.setupRoutes();
    }
    // </editor-fold>

    /**
     * Application's main function.
     * @param args The arguments.
     */
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
}
