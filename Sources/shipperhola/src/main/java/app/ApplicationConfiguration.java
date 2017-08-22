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

    private static final String DATA_SOURCE_URL_KEY = "dataSource.url";
    private static final String DATA_SOURCE_USER_KEY = "dataSource.user";
    private static final String DATA_SOURCE_PASSWORD_KEY = "dataSource.password";

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
     * Get the JDBC URL to connect to the data source.
     * @return The URL.
     */
    public String getDataSourceUrl() {
        return properties.getProperty(DATA_SOURCE_URL_KEY);
    }

    /**
     * Get the username to connect to the data source.
     * @return The username.
     */
    public String getDataSourceUser() {
        return properties.getProperty(DATA_SOURCE_USER_KEY);
    }

    /**
     * Get the password to connect to the data source.
     * @return The password.
     */
    public String getDataSourcePassword() {
        return properties.getProperty(DATA_SOURCE_PASSWORD_KEY);
    }
}
