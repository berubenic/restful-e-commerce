package com.example.assignment2_restful_ecommerce.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    /**
     * Maximum discount percentage.
     */
    public static final int MAX_DISCOUNT = 100;

    /**
     * Product repository.
     */
    private final ProductRepository productRepository;

    /**
     * Constructor.
     *
     * @param pProductRepository the product repository
     */
    public ProductService(final ProductRepository pProductRepository) {
        this.productRepository = pProductRepository;
    }

    /**
     * Get all products.
     *
     * @param keyword filter by name or description
     * @return list of products
     */
    public List<Product> getAllProducts(final String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return productRepository.findByKeyword(keyword);
        } else {
            return productRepository.findAll();
        }
    }

    /**
     * Get one product by id.
     *
     * @param id product id
     * @return product
     */
    public Optional<Product> getProductById(final Long id) {
        return productRepository.findById(id);
    }

    /**
     * Save a product.
     *
     * @param product product
     * @return saved product
     */
    public Product saveProduct(final Product product) {
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
     * Create a new product.
     *
     * @param product product
     * @return created product
     */
    public Product createProduct(final Product product) {
        return this.saveProduct(product);
    }

    /**
     * Update a product.
     * Uses a complete product object to update the existing product.
     *
     * @param id product id
     * @param newProduct new product object
     * @return updated product
     */
    public Product updateProduct(final Long id, final Product newProduct) {
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
     * Delete a product by id.
     *
     * @param id product id
     */
    public void deleteProduct(final Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);    }

    /**
     * Apply discount to all products.
     *
     * @param products list of products
     * @param discount discount percentage
     * @return list of products with discounted prices
     */
    public List<Product> applyDiscount(
            final List<Product> products,
            final int discount
    ) {
        if (discount < 0 || discount > MAX_DISCOUNT) {
            throw new IllegalArgumentException(
                    "discount must be between 0 and 100"
            );
        }
        return products.stream()
                .map(
                        product -> new Product(
                                product,
                                product.getDiscountedPrice(discount)
                        )
                )
                .collect(Collectors.toList());
    }

    /**
     * Apply discount to a product.
     *
     * @param product product
     * @param discount discount percentage
     * @return product with discounted price
     */
    public Product applyDiscount(final Product product, final int discount) {
        if (discount < 0 || discount > MAX_DISCOUNT) {
            throw new IllegalArgumentException(
                    "discount must be between 0 and 100"
            );
        }
        return new Product(product, product.getDiscountedPrice(discount));
    }
}
