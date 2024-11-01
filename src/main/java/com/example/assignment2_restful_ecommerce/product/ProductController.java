package com.example.assignment2_restful_ecommerce.product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = {"", "/"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    List<Product> all() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    Product one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
