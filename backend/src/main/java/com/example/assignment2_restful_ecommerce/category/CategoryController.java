package com.example.assignment2_restful_ecommerce.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    /**
     * Category service.
     */
    private final CategoryService categoryService;

    /**
     * Constructor.
     *
     * @param pCategoryService category service
     */
    public CategoryController(final CategoryService pCategoryService) {
        this.categoryService = pCategoryService;
    }

    /**
     * Get all categories.
     *
     * @return list of categories
     */
    @GetMapping(path = {"", "/"})
    public List<Category> all() {
        return categoryService.getAllCategories();
    }

    /**
     * Get one category by id.
     *
     * @param id category id
     */
    @GetMapping(path = {"/{id}"})
    public Category one(@PathVariable final Long id) {
        return categoryService.getCategoryById(id).orElseThrow(
            () -> new CategoryNotFoundException(id)
        );
    }

    /**
     * Create a category.
     *
     * @param category category
     * @return created category
     */
    @PostMapping(path = {"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody final Category category) {
        return categoryService.saveCategory(category);
    }

    /**
     * Update a category.
     *
     * @param id category id
     * @param category category
     * @return updated category
     */
    @PutMapping(path = {"/{id}"})
    public Category update(@PathVariable final Long id, @RequestBody final Category category) {
        return categoryService.updateCategory(id, category);
    }

    /**
     * Delete a category.
     *
     * @param id category id
     */
    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        categoryService.deleteCategory(id);
    }
}
