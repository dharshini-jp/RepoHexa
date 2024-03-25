package com.hexaware.controller;

import com.hexaware.exception.*;
import com.hexaware.dao.OrderDao;
import com.hexaware.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessor implements IOrderManagementRepository {
    private OrderDao orderDao;

    public OrderProcessor() {
        this.orderDao = new OrderDao();
    }

    @Override
    public void createOrder(User user, List<Product> products) throws UserNotFoundException {
        // Check if user exists in database, if not create user
        User existingUser = orderDao.getUserById(user.getUserId());
        if (existingUser == null) {
            createUser(user);
        }
        
        // Create order
        orderDao.createOrder(user, products);
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws UserNotFoundException, OrderNotFoundException {
        // Check if user exists in database
        User existingUser = orderDao.getUserById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException();
        }
        
        // Check if order exists in database
        if (!orderDao.orderExists(userId, orderId)) {
            throw new OrderNotFoundException();
        }
        
        // Cancel order
        orderDao.cancelOrder(userId, orderId);
    }

    @Override
    public void createProduct(User user, Product product) throws UserNotFoundException {
        // Check if user is an admin
        if (!"Admin".equals(user.getRole())) {
            throw new UserNotFoundException();
        }

        // Create product
        orderDao.createProduct(product);
    }

    @Override
    public void createUser(User user) {
        orderDao.createUser(user);
    }

    @Override
    public List<Product> getAllProducts() {
        return orderDao.getAllProducts();
    }

    @Override
    public List<Product> getOrderByUser(User user) throws UserNotFoundException {
        // Check if user exists in database
        User existingUser = orderDao.getUserById(user.getUserId());
        if (existingUser == null) {
            throw new UserNotFoundException();
        }

        // Return products ordered by the user
        return orderDao.getOrderByUser(user.getUserId());
    }
}                                