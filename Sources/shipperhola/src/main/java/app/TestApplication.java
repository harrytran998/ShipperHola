/*
 * Copyright © 2017 XVideos Team
 */
package app;

import dao.CategoryDao;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author nhattq
 */
public class TestApplication {

    public static void main(String[] args) throws Exception {
        ApplicationConfiguration configuration = ApplicationConfiguration.fromFile(Application.CONFIGURATION_FILE_NAME);
        DriverManagerDataSource dataSource = new DriverManagerDataSource(configuration.getDataSourceUrl(), configuration.getDataSourceUser(), configuration.getDataSourcePassword());
        CategoryDao categoryDao = new CategoryDao(dataSource);
        categoryDao.getAll().forEach(System.out::println);
    }
}
