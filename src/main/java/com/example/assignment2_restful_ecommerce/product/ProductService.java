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

    public List<Product> getAllProducts(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return productRepository.findByKeyword(keyword);
        } else {
            return productRepository.findAll();
        }
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new ProductNameMustBeUniqueException(product.getName());
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> applyDiscount(List<Product> products, int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return products.stream()
                .map(product -> new Product(product, product.getDiscountedPrice(discount)))
                .collect(Collectors.toList());
    }
}