package com.example.assignment2_restful_ecommerce.category;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.List;
import java.util.Optional;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final String xmlMediaType = "application/xml;charset=UTF-8";

    private ObjectMapper objectMapper;

    private XmlMapper xmlMapper;

    private final Category category1 = new Category("Category1", "Description1");

    private final Category category2 = new Category("Category2", "Description2");

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    @Test
    void allCategoriesJson() throws Exception {
        List<Category> categories = List.of(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories")
                       .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(categories)));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void allCategoriesXml() throws Exception {
        List<Category> categories = List.of(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories")
                       .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/List/item[1]/name").string(category1.getName()))
                .andExpect(xpath("/List/item[2]/name").string(category2.getName()));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void oneCategoryJson() throws Exception {
        category1.setId(1L);

        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category1));

        mockMvc.perform(get("/categories/1")
                       .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(category1)));
    }

    @Test
    void oneCategoryXml() throws Exception {
        category1.setId(1L);

        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category1));

        mockMvc.perform(get("/categories/1")
                       .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/category/name").string(category1.getName()));
    }

    @Test
    void oneCategoryNotFoundJson() throws Exception {
        when(categoryService.getCategoryById(1L)).thenThrow(new CategoryNotFoundException(1L));

        mockMvc.perform(get("/categories/1")
                       .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
