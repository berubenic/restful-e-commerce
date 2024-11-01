package com.example.assignment2_restful_ecommerce;


import com.example.assignment2_restful_ecommerce.product.Product;
import com.example.assignment2_restful_ecommerce.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            // office supplies
            repository.findByName("Pencil").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Pencil", "A pencil for writing", 0.50, 100, "pencil.jpg")));
                return null;
            });
            repository.findByName("Pen").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Pen", "A pen for writing", 1.00, 50, "pen.jpg")));
                return null;
            });
            repository.findByName("Eraser").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Eraser", "An eraser for erasing", 0.25, 200, "eraser.jpg")));
                return null;
            });
            // bathroom supplies
            repository.findByName("Toilet Paper").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Toilet Paper", "Toilet paper for wiping", 1.00, 100, "toilet_paper.jpg")));
                return null;
            });
            repository.findByName("Toothbrush").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Toothbrush", "A toothbrush for brushing", 2.00, 50, "toothbrush.jpg")));
                return null;
            });
            repository.findByName("Toothpaste").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Toothpaste", "Toothpaste for brushing", 3.00, 50, "toothpaste.jpg")));
                return null;
            });
            // food
            repository.findByName("Apple").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Apple", "A fruit", 0.50, 100, "apple.jpg")));
                return null;
            });
            repository.findByName("Banana").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Banana", "A fruit", 0.50, 100, "banana.jpg")));
                return null;
            });
            repository.findByName("Orange").orElseGet(() -> {
                log.info("Preloading {}", repository.save(new Product("Orange", "A fruit", 0.50, 100, "orange.jpg")));
                return null;
            });
        };
    }
}