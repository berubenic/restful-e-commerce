package com.example.assignment2_restful_ecommerce.product;

import com.example.assignment2_restful_ecommerce.PropertyMustNotBeBlankException;
import com.example.assignment2_restful_ecommerce.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Entity
@Table(name = "products")
@JacksonXmlRootElement(localName = "product")
public final class Product {

    /**
     * The maximum discount percentage.
     */
    private static final int MAX_DISCOUNT = 100;

    /**
     * The divisor for calculating the percentage.
     */
    private static final double PERCENTAGE_DIVISOR = 100.0;

    /**
     * The unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * The description of the product.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The price of the product.
     */
    @Column(nullable = false)
    private double price;

    /**
     * The stock quantity of the product.
     */
    @Column(nullable = false, name = "stock_quantity")
    private int stockQuantity;

    /**
     * The image path of the product.
     * Relative path from the resources/static/images folder.
     */
    @Column(name = "image_path")
    private String imagePath;

    /**
     * The category of the product.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Default constructor for the product.
     */
    public Product() {
    }

    /**
     * Constructor for the product.
     *
     * @param pName          the name of the product
     * @param pDescription   the description of the product
     * @param pPrice         the price of the product
     * @param pStockQuantity the stock quantity of the product
     * @param pImagePath     the image path of the product
     * @param pCategory      the category of the product
     */
    public Product(
            final String pName,
            final String pDescription,
            final double pPrice,
            final int pStockQuantity,
            final String pImagePath,
            final Category pCategory
    ) {
        this.name = pName;
        this.description = pDescription;
        this.price = pPrice;
        this.stockQuantity = pStockQuantity;
        this.imagePath = pImagePath;
        this.category = pCategory;
    }

    /**
     * Constructor for the product with a discounted price.
     *
     * @param product         the product to copy
     * @param discountedPrice the discounted price of the product
     */
    public Product(final Product product, final double discountedPrice) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = discountedPrice;
        this.stockQuantity = product.getStockQuantity();
        this.imagePath = product.getImagePath();
        this.category = product.getCategory();
    }

    /**
     * Get the category of the product.
     *
     * @return the category of the product
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set the category of the product.
     *
     * @param pCategory the category of the product
     */
    public void setCategory(final Category pCategory) {
        this.category = pCategory;
    }

    /**
     * Get the unique identifier of the product.
     *
     * @return the unique identifier of the product
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the product.
     *
     * @param pId the unique identifier of the product
     */
    public void setId(final Long pId) {
        this.id = pId;
    }

    /**
     * Get the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the discounted price of the product.
     *
     * @param discount the discount percentage
     * @return the discounted price of the product
     */
    public double getDiscountedPrice(final int discount) {
        if (discount < 0 || discount > MAX_DISCOUNT) {
            throw new IllegalArgumentException(
                    "discount must be between 0 and 100"
            );
        }
        return Math.round(
                price * (1 - discount / PERCENTAGE_DIVISOR) * PERCENTAGE_DIVISOR
        ) / PERCENTAGE_DIVISOR;
    }

    /**
     * Get the stock quantity of the product.
     *
     * @return the stock quantity of the product
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Get the image path of the product.
     *
     * @return the image path of the product
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Set the name of the product.
     *
     * @param pName the name of the product
     */
    public void setName(final String pName) {
        this.name = pName;
    }

    /**
     * Set the description of the product.
     *
     * @param pDescription the description of the product
     */
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /**
     * Set the price of the product.
     *
     * @param pPrice the price of the product
     */
    public void setPrice(final double pPrice) {
        if (pPrice < 0) {
            throw new ProductPriceMustBePositiveException();
        }

        this.price = pPrice;
    }

    /**
     * Set the stock quantity of the product.
     *
     * @param pStockQuantity the stock quantity of the product
     */
    public void setStockQuantity(final int pStockQuantity) {
        if (pStockQuantity < 0) {
            throw new IllegalArgumentException(
                    "stock quantity must be greater than or equal to 0"
            );
        }

        this.stockQuantity = pStockQuantity;
    }

    /**
     * Set the image path of the product.
     *
     * @param pImagePath the image path of the product
     */
    public void setImagePath(final String pImagePath) {
        this.imagePath = pImagePath;
    }

    public void validate() {
        if (name == null || name.isBlank()) {
            throw new PropertyMustNotBeBlankException("name");
        }
        if (description == null || description.isBlank()) {
            throw new PropertyMustNotBeBlankException("description");
        }
        if (price < 0) {
            throw new ProductPriceMustBePositiveException();
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException(
                    "stock quantity must be greater than or equal to 0"
            );
        }
        if (imagePath == null || imagePath.isBlank()) {
            throw new PropertyMustNotBeBlankException("imagePath");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Product["
                        + "id=%d, name='%s', description='%s', "
                        + "price=%.2f, stockQuantity=%d, imagePath='%s'"
                        + "]",
                id, name, description, price, stockQuantity, imagePath
        );
    }
}
