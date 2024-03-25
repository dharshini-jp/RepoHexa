package com.hexaware.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.model.*;
import com.hexaware.util.DBUtil;

public class OrderDao {
	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	
	public void createUser(User user) {
		
		con = DBUtil.getDBConn();
		try {
			con = DBUtil.getDBConn();
			ps = con.prepareStatement("insert into user(userid,username,role) values(?,?,?)");
			ps.setInt(1, user.getUserId());
			ps.setString(2,user.getUsername());
			ps.setString(3,user.getRole());
			
			
			int noofrows = ps.executeUpdate();
			System.out.println("No.of rows inserted in db : "+noofrows);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void showUser() {
		con = DBUtil.getDBConn();
		
	}
	public void updateUser() {
		con = DBUtil.getDBConn();
		
	}
	public void deleteUser() {
		con = DBUtil.getDBConn();
		
	}
	
	
	
	public void createProduct(Product product) {
        con = DBUtil.getDBConn();
        try {
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                ps = con.prepareStatement("INSERT INTO electronics(productid, productname, description, type, brand, warrantyperiod) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, electronics.getProductId());
                ps.setString(2, electronics.getProductName());
                ps.setString(3, electronics.getDescription());
                ps.setString(4, electronics.getType());
                ps.setString(5, electronics.getBrand());
                ps.setInt(6, electronics.getWarrantyPeriod());
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                ps = con.prepareStatement("INSERT INTO clothing(productid, productname, description, type, size, color) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, clothing.getProductId());
                ps.setString(2, clothing.getProductName());
                ps.setString(3, clothing.getDescription());
                ps.setString(4, clothing.getType());
                ps.setString(5, clothing.getSize());
                ps.setString(6, clothing.getColor());
            } else {
                ps = con.prepareStatement("INSERT INTO product(productid, productname, description, type) VALUES (?, ?, ?, ?)");
                ps.setInt(1, product.getProductId());
                ps.setString(2, product.getProductName());
                ps.setString(3, product.getDescription());
                ps.setString(4, product.getType());
            }

            int noOfRows = ps.executeUpdate();
            System.out.println("No. of rows inserted in db : " + noOfRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void showProduct() {
		con = DBUtil.getDBConn();
		
	}
	public void updateProduct() {
		con = DBUtil.getDBConn();
		
	}
	public void deleteProduct() {
		con = DBUtil.getDBConn();
		
	}
	
	
	public void createOrder(User user, List<Product> products) {
	    con = DBUtil.getDBConn();
	    try {
	        // Check if the user exists in the database
	        if (getUserById(user.getUserId()) == null) {
	            // If the user does not exist, create the user
	            createUser(user);
	        }

	        // Generate an order ID (you can use a sequence or any other method to generate unique IDs)
	        int orderId = generateOrderId();

	        // Insert the order details into the database
	        ps = con.prepareStatement("INSERT INTO orders(orderId, userId, productId) VALUES (?, ?, ?)");
	        for (Product product : products) {
	            ps.setInt(1, orderId);
	            ps.setInt(2, user.getUserId());
	            ps.setInt(3, product.getProductId());
	            ps.executeUpdate();
	        }
	        System.out.println("Order created successfully");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	private int generateOrderId() {
	    // Assuming you have a connection to your database
	    try {
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT MAX(orderId) FROM orders");
	        if (rs.next()) {
	            // Get the maximum order ID from the database and increment it by 1
	            int maxOrderId = rs.getInt(1);
	            return maxOrderId + 1;
	        } else {
	            // If no orders exist yet, start with order ID 1
	            return 1;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; // Return -1 to indicate an error
	    }
	}

	
	public User getUserById(int userId) {
        con = DBUtil.getDBConn();
        try {
            ps = con.prepareStatement("SELECT * FROM user WHERE userid = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public boolean orderExists(int userId, int orderId) {
        con = DBUtil.getDBConn();
        try {
            ps = con.prepareStatement("SELECT COUNT(*) FROM orders WHERE userid = ? AND orderid = ?");
            ps.setInt(1, userId);
            ps.setInt(2, orderId);
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public void cancelOrder(int userId, int orderId) {
        con = DBUtil.getDBConn();
        try {
            ps = con.prepareStatement("DELETE FROM orders WHERE userid = ? AND orderid = ?");
            ps.setInt(1, userId);
            ps.setInt(2, orderId);

            int noOfRows = ps.executeUpdate();
            if (noOfRows == 0) {
                System.out.println("Order not found with ID: " + orderId + " for user ID: " + userId);
            } else {
                System.out.println("Order canceled successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	 public List<Product> getAllProducts() {
	        con = DBUtil.getDBConn();
	        List<Product> productList = new ArrayList<>();
	        try {
	            ps = con.prepareStatement("SELECT * FROM product");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("productid"));
	                product.setProductName(rs.getString("productname"));
	                product.setDescription(rs.getString("description"));
	                product.setType(rs.getString("type"));
	                productList.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productList;
	    }
	
	
	 public List<Product> getOrderByUser(int userId) {
	        con = DBUtil.getDBConn();
	        List<Product> productList = new ArrayList<>();
	        try {
	            ps = con.prepareStatement("SELECT * FROM orders WHERE userid = ?");
	            ps.setInt(1, userId);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("productid"));
	                product.setProductName(rs.getString("productname"));
	                product.setDescription(rs.getString("description"));
	                product.setType(rs.getString("type"));
	                productList.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productList;
	    }

	
	
	
	
	
	
}