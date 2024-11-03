package com.example.assignment2_restful_ecommerce.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     *
     * @param keyword filter by name or description
     * @return list of products
     */
    public List<Product> getAllProducts(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return productRepository.findByKeyword(keyword);
        } else {
            return productRepository.findAll();
        }
    }

    /**
     * Get one product by id
     *
     * @param id product id
     * @return product
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Save a product
     *
     * @param product product
     * @return saved product
     */
    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                throw new ProductNameMustBeUniqueException(product.getName());
            }
            throw e;
        }
    }

    /**
     * Update a product.
     * Uses a complete product object to update the existing product.
     *
     * @param id product id
     * @param newProduct new product object
     * @return updated product
     */
    public Product updateProduct(Long id, Product newProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setStockQuantity(newProduct.getStockQuantity());
                    product.setImagePath(newProduct.getImagePath());
                    return this.saveProduct(product);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Delete a product by id
     *
     * @param id product id
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Apply discount to all products
     *
     * @param products list of products
     * @param discount discount percentage
     * @return list of products with discounted prices
     */
    public List<Product> applyDiscount(List<Product> products, int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }
        return products.stream()
                .map(product -> new Product(product, product.getDiscountedPrice(discount)))
                .collect(Collectors.toList());
    }

    /**
     * Apply discount to a product
     *
     * @param product product
     * @param discount discount percentage
     * @return product with discounted price
     */
    public Product applyDiscount(Product product, int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }
        return new Product(product, product.getDiscountedPrice(discount));
    }
}