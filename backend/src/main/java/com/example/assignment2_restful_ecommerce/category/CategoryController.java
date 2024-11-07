package com.example.assignment2_restful_ecommerce.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, Object> handleCategoryNotFoundException(CategoryNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", e.getMessage());
        return response;
    }
}
