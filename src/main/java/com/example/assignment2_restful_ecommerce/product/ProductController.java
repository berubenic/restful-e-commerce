package com.example.assignment2_restful_ecommerce.product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all products
     *
     * @param keyword filter by name or description
     * @param discount apply given discount to all products
     * @return list of products
     */
    @GetMapping(path = {"", "/"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Product> all(@RequestParam(required = false) String keyword, @RequestParam(required = false) Integer discount) {
        List<Product> products = productService.getAllProducts(keyword);

        if (discount != null) {
            products = productService.applyDiscount(products, discount);
        }

        return products;
    }

    /**
     * Get one product by id
     *
     * @param id product id
     * @param discount apply given discount to the product
     * @return product
     */
    @GetMapping(path = {"/{id}"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Product one(@PathVariable Long id, @RequestParam(required = false) Integer discount) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (discount != null) {
            product = productService.applyDiscount(product, discount);
        }

        return product;
    }

    /**
     * Update a product.
     * Uses a complete product object to update the existing product.
     *
     * @param id product id
     * @param newProduct new product object
     * @return updated product
     */
    @PutMapping(path = {"/{id}"}, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Product update(@PathVariable Long id, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct);
    }
}