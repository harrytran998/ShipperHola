/*
 * Copyright © 2017 XVideos Team
 */
package app;

import dao.AccountDao;
import dao.CartItemDao;
import dao.CategoryDao;
import dao.OrderDao;
import dao.OrderMessageDao;
import dao.ProductCommentDao;
import dao.ProductDao;
import dao.ProductPictureDao;
import dao.ProductReviewDao;
import dao.ShippingAddressDao;
import model.Category;
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
        categoryDao.add(new Category(null,"Do Shit"));

        
//        CartItemDao cartItemDao = new CartItemDao(dataSource);
//        cartItemDao.getbyAccountId(1).forEach(System.out::println);
//        ProductCommentDao productCommentDao = new ProductCommentDao(dataSource);
//        productCommentDao.getByAccountId(1).forEach(System.out::println);
//
//        ProductDao productDao = new ProductDao(dataSource);
//        productDao.search(null, null, null).forEach(System.out::println);
//        ProductPictureDao productPictureDao = new ProductPictureDao(dataSource);
//        

          
//        System.out.println(productPictureDao.getById(1));
//        ProductReviewDao productReviewDao = new ProductReviewDao(dataSource);
//        System.out.println(productReviewDao.getById(1));
//        ShippingAddressDao shippingAddressDao = new ShippingAddressDao(dataSource);
//        System.out.println(shippingAddressDao.getById(1));
//        AccountDao accountDao = new AccountDao(dataSource);
//        System.out.println(accountDao.getById(1));
//        OrderDao orderDao = new OrderDao(dataSource);
//        System.out.println(orderDao.getById(1));
//        OrderMessageDao orderMessageDao = new OrderMessageDao(dataSource);
//        System.out.println(orderMessageDao.getById(1));
    }
}