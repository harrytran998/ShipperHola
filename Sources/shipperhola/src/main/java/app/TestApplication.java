/*
 * Copyright © 2017 XVideos Team
 */
package app;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author nhattq
 */
public class TestApplication {
    
    public static void main(String[] args) throws Exception {
        ApplicationConfiguration configuration = ApplicationConfiguration.fromFile("application.properties");
        DriverManagerDataSource dataSource = new DriverManagerDataSource(configuration.getDataSourceUrl(), configuration.getDataSourceUser(), configuration.getDataSourcePassword());
    }
}