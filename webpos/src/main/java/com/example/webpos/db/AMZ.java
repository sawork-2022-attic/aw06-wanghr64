package com.example.webpos.db;

import com.example.webpos.model.Product;
import org.springframework.cache.annotation.Cacheable;

import java.sql.*;
import java.util.*;

public class AMZ implements PosDB {

    private List<Product> products = null;

    static final String USER = "root";
    static final String PASS = "7148123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn;

    private Random priceGenerator;

    public AMZ() throws Exception {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        priceGenerator = new Random();
    }

    @Override
    @Cacheable(value = "products")
    public List<Product> getProducts() {
        products = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM amazon;");
            while (rs.next()) {
                products.add(new Product(rs.getString("asin"), rs.getString("title"), priceGenerator.nextInt(20) + 10,
                        rs.getString("imgURLHighRes")));
            }
        } catch (Exception e) {
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

}
