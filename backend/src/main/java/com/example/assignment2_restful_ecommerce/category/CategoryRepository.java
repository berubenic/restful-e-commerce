package com.example.assignment2_restful_ecommerce.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Find a category by its name.
     *
     * @param name the name of the category
     * @return the category with the given name
     */
    Optional<Category> findByName(String name);
}
