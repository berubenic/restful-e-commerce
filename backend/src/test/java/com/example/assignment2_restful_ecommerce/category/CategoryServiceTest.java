package com.example.assignment2_restful_ecommerce.category;

import com.example.assignment2_restful_ecommerce.PropertyMustBeUniqueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category1 = new Category(
                "Category1",
                "Description1"
        );
    }

    @Test
    void getAllCategories_returnsAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(categories, result);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getCategoryById_existingId_returnsCategory() {
        Long id = 1L;
        Category category = new Category();
        when(categoryRepository.findById(id)).thenReturn(java.util.Optional.of(category));

        java.util.Optional<Category> result = categoryService.getCategoryById(id);

        assertEquals(java.util.Optional.of(category), result);
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void getCategoryById_nonExistingId_returnsEmpty() {
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(java.util.Optional.empty());

        java.util.Optional<Category> result = categoryService.getCategoryById(id);

        assertEquals(java.util.Optional.empty(), result);
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void saveCategory_returnsSavedCategory() {
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category result = categoryService.saveCategory(category1);

        assertEquals(category1, result);
        verify(categoryRepository, times(1)).save(category1);
    }

    @Test
    void saveCategory_nameAlreadyExists_throwsException() {
        category1.setName("duplicate");
        DataIntegrityViolationException exception = new DataIntegrityViolationException(
                "duplicate", new org.hibernate.exception.ConstraintViolationException("", null, "")
        );
        when(categoryRepository.save(category1)).thenThrow(exception);

        assertThrows(PropertyMustBeUniqueException.class, () -> categoryService.saveCategory(category1));
    }

    @Test
    void updateCategory_existingId_updatesCategory() {
        Long id = 1L;
        Category category = category1;
        when(categoryRepository.findById(id)).thenReturn(java.util.Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.updateCategory(id, category);

        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void updateCategory_nonExistingId_throwsException() {
        Long id = 1L;
        Category category = new Category();
        when(categoryRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(id, category));
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void deleteCategory_existingId_deletesCategory() {
        Long id = 1L;
        when(categoryRepository.existsById(id)).thenReturn(true);

        categoryService.deleteCategory(id);

        verify(categoryRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteCategory_nonExistingId_throwsException() {
        Long id = 1L;
        when(categoryRepository.existsById(id)).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(id));
        verify(categoryRepository, times(1)).existsById(id);
    }
}
