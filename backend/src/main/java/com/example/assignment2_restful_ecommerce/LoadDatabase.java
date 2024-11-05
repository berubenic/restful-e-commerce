package com.example.assignment2_restful_ecommerce;

import com.example.assignment2_restful_ecommerce.category.Category;
import com.example.assignment2_restful_ecommerce.category.CategoryRepository;
import com.example.assignment2_restful_ecommerce.product.Product;
import com.example.assignment2_restful_ecommerce.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    /**
     * Logger for this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoadDatabase.class
    );

    @Bean
    CommandLineRunner initDatabase(
            final ProductRepository productRepository,
            final CategoryRepository categoryRepository
    ) {
        return args -> {
            // Preload categories
            Category officeCategory = preloadCategory(
                    categoryRepository,
                    "Office Supplies",
                    "Office supplies"
            );
            Category bathroomCategory = preloadCategory(
                    categoryRepository,
                    "Bathroom Supplies",
                    "Bathroom supplies"
            );
            Category foodCategory = preloadCategory(
                    categoryRepository,
                    "Food",
                    "Food"
            );

            // Preload products
            preloadProduct(
                    productRepository,
                    "Pencil",
                    "A pencil for writing",
                    0.50,
                    100,
                    "pencil.jpg",
                    officeCategory
            );
            preloadProduct(
                    productRepository,
                    "Pen",
                    "A pen for writing",
                    1.00,
                    50,
                    "pen.jpg",
                    officeCategory
            );
            preloadProduct(
                    productRepository,
                    "Eraser",
                    "An eraser for erasing",
                    0.25,
                    200,
                    "eraser.jpg",
                    officeCategory
            );
            preloadProduct(
                    productRepository,
                    "Toilet Paper",
                    "Toilet paper for wiping",
                    1.00,
                    100,
                    "toilet_paper.jpg",
                    bathroomCategory
            );
            preloadProduct(
                    productRepository,
                    "Toothbrush",
                    "A toothbrush for brushing",
                    2.00,
                    50,
                    "toothbrush.jpg",
                    bathroomCategory
            );
            preloadProduct(
                    productRepository,
                    "Toothpaste",
                    "Toothpaste for brushing",
                    3.00,
                    50,
                    "toothpaste.jpg",
                    bathroomCategory
            );
            preloadProduct(
                    productRepository,
                    "Apple",
                    "A fruit",
                    0.50,
                    100,
                    "apple.jpg",
                    foodCategory
            );
            preloadProduct(
                    productRepository,
                    "Banana",
                    "A fruit",
                    0.50,
                    100,
                    "banana.jpg",
                    foodCategory
            );
            preloadProduct(
                    productRepository,
                    "Orange",
                    "A fruit",
                    0.50,
                    100,
                    "orange.jpg",
                    foodCategory
            );
        };
    }

    private Category preloadCategory(
            final CategoryRepository categoryRepository,
            final String name,
            final String description
    ) {
        return categoryRepository.findByName(name).orElseGet(() -> {
            this.log(categoryRepository.save(new Category(name, description)));
            return categoryRepository.findByName(name).orElse(null);
        });
    }

    private void preloadProduct(
            final ProductRepository productRepository,
            final String name,
            final String description,
            final double price,
            final int stockQuantity,
            final String imagePath,
            final Category category
    ) {
        productRepository.findByName(name).orElseGet(() -> {
            this.log(
                    productRepository.save(
                            new Product(
                                    name,
                                    description,
                                    price,
                                    stockQuantity,
                                    imagePath,
                                    category
                            )
                    )
            );
            return null;
        });
    }

    private void log(final Object object) {
        LOG.info("Preloading {}", object);
    }
}
