package com.example.assignment2_restful_ecommerce.product;

import jakarta.persistence.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Entity
@Table(name = "products")
@JacksonXmlRootElement(localName = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock_quantity;

    @Column(nullable = false)
    private String image_path; // relative path from the resources/static/images folder
    // TODO: add a relationship to the Category entity

    protected Product() {}

    // Product default constructor
    public Product(String name, String description, double price, int stock_quantity, String image_path) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.image_path = image_path;
    }

    // Product constructor with discounted price
    public Product(Product product, double discountedPrice) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = discountedPrice;
        this.stock_quantity = product.getStockQuantity();
        this.image_path = product.getImagePath();
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', description='%s', price=%.2f, stock_quantity=%d, image_path='%s']",
                id, name, description, price, stock_quantity, image_path
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountedPrice(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }
        return Math.round(price * (1 - discount / 100.0) * 100.0) / 100.0;
    }

    public int getStockQuantity() {
        return stock_quantity;
    }

    public String getImagePath() {
        return image_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must be greater than or equal to 0");
        }

        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("stock quantity must be greater than or equal to 0");
        }

        this.stock_quantity = stockQuantity;
    }

    public void setImagePath(String imagePath) {
        this.image_path = imagePath;
    }
}
