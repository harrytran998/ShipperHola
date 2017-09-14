/*
 * Copyright © 2017 XVideos Team
 */
package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Le Cao Nguyen
 */
public class ApplicationConfiguration {
    public static final int DEFAULT_SERVER_PORT = 4567;
    
    private static final String DEVELOPMENT_MODE_KEY = "developmentMode";
    private static final String DATA_SOURCE_URL_KEY = "dataSource.url";
    private static final String DATA_SOURCE_USER_KEY = "dataSource.user";
    private static final String DATA_SOURCE_PASSWORD_KEY = "dataSource.password";
    private static final String THYMELEAF_CACHEABLE_KEY = "thymeleaf.cacheable";
    private static final String SERVER_PORT_KEY = "server.port";
    
    private final Properties properties;

    /**
     * Construct an application configuration from a property object.
     *
     * @param properties The property object.
     */
    public ApplicationConfiguration(Properties properties) {
        this.properties = properties;
    }

    /**
     * Construct an application configuration from a file.
     *
     * @param fileName The configuration file name.
     * @return The configuration object loaded from the file.
     * @throws IOException If the configuration file does not exist, or if an
     * error occurred while reading from the configuration file.
     */
    public static ApplicationConfiguration fromFile(String fileName) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Could not locate the configuration file at " + fileName + ".");
        }
        Properties props = new Properties();
        props.load(inputStream);
        return new ApplicationConfiguration(props);
    }

    /**
     * Check whether the application will be run in development mode.
     *
     * @return True/False
     */
    public boolean isDevelopmentMode() {
        return Boolean.parseBoolean(properties.getProperty(DEVELOPMENT_MODE_KEY));
    }

    /**
     * Get the JDBC URL to connect to the data source.
     *
     * @return The URL.
     */
    public String getDataSourceUrl() {
        return properties.getProperty(DATA_SOURCE_URL_KEY);
    }

    /**
     * Get the username to connect to the data source.
     *
     * @return The username.
     */
    public String getDataSourceUser() {
        return properties.getProperty(DATA_SOURCE_USER_KEY);
    }

    /**
     * Get the password to connect to the data source.
     *
     * @return The password.
     */
    public String getDataSourcePassword() {
        return properties.getProperty(DATA_SOURCE_PASSWORD_KEY);
    }

    /**
     * Check whether the Thymeleaf template engine will be configured to cache
     * the template files.
     * 
     * @return True/False
     */
    public boolean isThymeleafCacheable() {
        return Boolean.parseBoolean(properties.getProperty(THYMELEAF_CACHEABLE_KEY));
    }
    
    /**
     * Return Spark server port.
     * @return Port number.
     */
    public int getServerPort() {
        try {
            return Integer.parseUnsignedInt(properties.getProperty(SERVER_PORT_KEY));
        } catch (NumberFormatException ex) {
            return DEFAULT_SERVER_PORT;
        }
    }
}
