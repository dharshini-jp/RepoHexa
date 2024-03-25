package com.hexaware.main;

import java.util.Scanner;
import com.hexaware.controller.OrderProcessor;
import com.hexaware.model.User;
import com.hexaware.model.Product;
import com.hexaware.model.Electronics;
import com.hexaware.model.Clothing;

public class OrderManagement {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Cancel Order");
            System.out.println("4. Get All Products");
            System.out.println("5. Get Order by User");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (choice) {
                case 1:
                    System.out.println("Enter User ID:");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    System.out.println("Enter Username:");
                    String username = scanner.nextLine();
                    
                    System.out.println("Enter Role (Admin/User):");
                    String role = scanner.nextLine();
                    
                    User user = new User(userId, username, role, username);
                    orderProcessor.createUser(user);
                    break;
                    
                case 2:
                    System.out.println("Enter Product ID:");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    System.out.println("Enter Product Name:");
                    String productName = scanner.nextLine();
                    
                    System.out.println("Enter Description:");
                    String description = scanner.nextLine();
                    
                    System.out.println("Enter Type (Electronics/Clothing):");
                    String type = scanner.nextLine();
                    
                    if ("Electronics".equalsIgnoreCase(type)) {
                        System.out.println("Enter Brand:");
                        String brand = scanner.nextLine();
                        
                        System.out.println("Enter Warranty Period:");
                        int warrantyPeriod = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        
                        Product electronicsProduct = new Electronics(productId, productName, description, userId, userId, type, brand, warrantyPeriod);
                        orderProcessor.createProduct(user,electronicsProduct);
                        
                    } else if ("Clothing".equalsIgnoreCase(type)) {
                        System.out.println("Enter Size:");
                        String size = scanner.nextLine();
                        
                        System.out.println("Enter Color:");
                        String color = scanner.nextLine();
                        
                        Product clothingProduct = new Clothing(productId, productName, description, userId, userId, type, size, color);
                        orderProcessor.createProduct(user,clothingProduct);
                    } else {
                        Product genericProduct = new Product(productId, productName, description, userId, userId, type);
                        orderProcessor.createProduct(user, genericProduct);
                    }
                    break;
                    
                case 3:
                    System.out.println("Enter User ID:");
                    int cancelUserId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    System.out.println("Enter Order ID:");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    // Implement cancelOrder functionality
                    orderProcessor.cancelOrder(cancelUserId, orderId);
                    break;
                    
                case 4:
                    // Implement getAllProducts functionality
                    System.out.println("All Products:");
                    for (Product product : orderProcessor.getAllProducts()) {
                        System.out.println(product);
                    }
                    break;
                    
                case 5:
                    System.out.println("Enter User ID:");
                    int userIdForOrder = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    // Implement getOrderbyUser functionality
                    System.out.println("Orders by User:");
                    for (Product product : orderProcessor.getOrderByUser(userIdForOrder)) {
                        System.out.println(product);
                    }
                    break;
                    
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
