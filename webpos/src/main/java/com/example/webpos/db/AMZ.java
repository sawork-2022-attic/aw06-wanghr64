package com.example.webpos.db;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.sql.*;

@Repository
public class AMZ implements PosDB {

    private List<Product> products = null;

    static final String USER = "root";
    static final String PASS = "7148123";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn;

    private Random priceGenerator;

    private ResultSet rs;

    public AMZ() {
        System.out
                .print("\n\n\n\n\n\n\n\n\n\n\n\nsssssssssssssssssssssssssasdasdasdasdasdasdasd\n\n\n\n\n\n\n\n\n\n\n");
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            priceGenerator = new Random();
            rs = conn.createStatement().executeQuery("SELECT * FROM amazon LIMIT 500;");
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nFailed to connect.\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    @Override
    @Cacheable(value = "products")
    public List<Product> getProducts() {
        System.out.println("Try to get Products.");
        products = new ArrayList<>();
        try {

            while (rs.next()) {
                products.add(new Product(rs.getString("asin"), rs.getString("title"), priceGenerator.nextInt(20) + 10,
                        rs.getString("imageURLHighRes")));

            }
        } catch (Exception e) {
            System.out.println("Failed to get Products.");
            System.out.println(e);
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
