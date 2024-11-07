package com.example.assignment2_restful_ecommerce.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    /**
     * Category repository.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructor.
     *
     * @param pCategoryRepository the category repository
     */
    public CategoryService(final CategoryRepository pCategoryRepository) {
        this.categoryRepository = pCategoryRepository;
    }

    /**
     * Get all categories.
     *
     * @return list of categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get one category by id.
     *
     * @param id category id
     * @return category
     */
    public Optional<Category> getCategoryById(final Long id) {
        return categoryRepository.findById(id);
    }
}
