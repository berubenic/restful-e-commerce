/**
 * This package contains the Category entity and related classes.
 */
package com.example.assignment2_restful_ecommerce.category;

import com.example.assignment2_restful_ecommerce.PropertyMustNotBeBlankException;
import com.example.assignment2_restful_ecommerce.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "categories")
@JacksonXmlRootElement(localName = "category")
public final class Category {

    /**
     * The id of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * The description of the category.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The list of products in the category.
     */
    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> products;

    /**
     * Default constructor for the Category class.
     */
    public Category() {
    }

    /**
     * Constructor for the Category class.
     *
     * @param pName        The name of the category.
     * @param pDescription The description of the category.
     */
    public Category(final String pName, final String pDescription) {
        this.name = pName;
        this.description = pDescription;
    }

    /**
     * Get the id of the category.
     *
     * @return The id of the category.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id of the category.
     *
     * @param pId The id to set for the category.
     */
    public void setId(long pId) {
        this.id = pId;
    }

    /**
     * Get the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the category.
     *
     * @param pName The name to set for the category.
     */
    public void setName(final String pName) {
        this.name = pName;
    }

    /**
     * Get the description of the category.
     *
     * @return The description of the category.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the category.
     *
     * @param pDescription The description to set for the category.
     */
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /**
     * Get products in the category.
     *
     * @return The list of products in the category.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set products in the category.
     *
     * @param pProducts The list of products to set in the category.
     */
    public void setProducts(final List<Product> pProducts) {
        this.products = pProducts;
    }

    /**
     * Validate the category.
     */
    public void validate() {
        if (name == null || name.isBlank()) {
            throw new PropertyMustNotBeBlankException("name");
        }
        if (description == null || description.isBlank()) {
            throw new PropertyMustNotBeBlankException("name");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Category[id=%d, name='%s', description='%s']",
                id, name, description
        );
    }
}
