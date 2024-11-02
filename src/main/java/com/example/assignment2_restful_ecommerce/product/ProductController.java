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

    @GetMapping(path = {"", "/"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Product> all(@RequestParam(required = false) String keyword, @RequestParam(required = false) Integer discount) {
        List<Product> products = productService.getAllProducts(keyword);

        if (discount != null) {
            products = productService.applyDiscount(products, discount);
        }

        return products;
    }

    @GetMapping(path = {"/{id}"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Product one(@PathVariable Long id, @RequestParam(required = false) Integer discount) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (discount != null) {
            product = new Product(product, product.getDiscountedPrice(discount));
        }

        return product;
    }

    @PutMapping(path = {"/{id}"}, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Product update(@PathVariable Long id, @RequestBody Product newProduct) {
        return productService.getProductById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setStockQuantity(newProduct.getStockQuantity());
                    product.setImagePath(newProduct.getImagePath());
                    return productService.saveProduct(product);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}