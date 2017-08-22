package app;

import controller.IndexController;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.TemplateEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import static spark.Spark.*;

public class Application {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final boolean IS_RUNNING_ON_LOCALHOST = true;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Configuration & dependencies">
    private static TemplateEngine templateEngine;

    // <editor-fold defaultstate="collapsed" desc="Getters for dependencies">
    public static TemplateEngine getTemplateEngine() {
        return templateEngine;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Main methods">
    private static void loadConfiguration() {
        
    }
    
    private static void initializeModules() {
        templateEngine = new ThymeleafTemplateEngine();
    }

    private static void configureServer() {
        if (IS_RUNNING_ON_LOCALHOST) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }
    }

    private static void setupRoutes() {
        IndexController.setupRoutes();
    }
    // </editor-fold>

    public static void main(String[] args) {
        try {
            loadConfiguration();
            initializeModules();
            configureServer();
            setupRoutes();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
