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
    private String name;
    private String description;
    private double price;
    private int stock_quantity;
    private String image_path; // relative path from the resources/static/images folder
    // TODO: add a relationship to the Category entity

    protected Product() {}

    public Product(String name, String description, double price, int stock_quantity, String image_path) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.image_path = image_path;
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

    public int getStockQuantity() {
        return stock_quantity;
    }

    public String getImagePath() {
        return image_path;
    }
}
