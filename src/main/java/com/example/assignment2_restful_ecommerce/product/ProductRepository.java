package com.example.assignment2_restful_ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Find a product by its name.
     *
     * @param name the name of the product
     * @return the product
     */
    Optional<Product> findByName(String name);

    /**
     * Find products by keyword.
     *
     * @param keyword the keyword
     * @return the products
     */
    @Query(
            "SELECT p"
                    + " FROM Product p"
                    + " WHERE p.name "
                    + "LIKE %:keyword% OR p.description LIKE %:keyword%"
    )
    List<Product> findByKeyword(@Param("keyword") String keyword);
}
