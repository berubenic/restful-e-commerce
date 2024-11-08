package com.example.assignment2_restful_ecommerce.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * Product service.
     */
    private final ProductService productService;

    /**
     * Constructor.
     *
     * @param pProductService product service
     */
    public ProductController(final ProductService pProductService) {
        this.productService = pProductService;
    }

    /**
     * Get all products.
     *
     * @param keyword  filter by name or description
     * @param discount apply given discount to all products
     * @return list of products
     */
    @GetMapping(path = {"", "/"})
    public List<Product> all(
            @RequestParam(required = false) final String keyword,
            @RequestParam(required = false) final Integer discount
    ) {
        List<Product> products = productService.getAllProducts(keyword);

        if (discount != null) {
            products = productService.applyDiscount(products, discount);
        }

        return products;
    }

    /**
     * Get one product by id.
     *
     * @param id       product id
     * @param discount apply given discount to the product
     * @return product
     */
    @GetMapping(path = {"/{id}"})
    public Product one(
            @PathVariable final Long id,
            @RequestParam(required = false) final Integer discount
    ) {
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
     * @param id         product id
     * @param newProduct new product object
     * @return updated product
     */
    @PutMapping(path = {"/{id}"})
    public Product update(
            @PathVariable final Long id,
            @RequestBody final Product newProduct
    ) {
        return productService.updateProduct(id, newProduct);
    }

    /**
     * Create a new product.
     *
     * @param newProduct new product object
     * @return created product
     */
    @PostMapping(path = {"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(
            @RequestBody final Product newProduct
    ) {
        return productService.saveProduct(newProduct);
    }

    /**
     * Delete a product.
     *
     * @param id product id
     */
    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable final Long id
    ) {
        productService.deleteProduct(id);
    }
}
