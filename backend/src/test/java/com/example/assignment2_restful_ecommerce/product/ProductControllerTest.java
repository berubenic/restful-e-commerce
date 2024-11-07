package com.example.assignment2_restful_ecommerce.product;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.assignment2_restful_ecommerce.category.Category;
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

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final String xmlMediaType = "application/xml;charset=UTF-8";

    private ObjectMapper objectMapper;

    private final XmlMapper xmlMapper = new XmlMapper();

    private final Product product1 = new Product(
            "Product1",
            "Description1",
            100.0,
            100,
            "image1.jpg",
            new Category("Category1", "Description1")
    );

    private final Product product2 = new Product(
            "Product2",
            "Description2",
            200.0,
            200,
            "image2.jpg",
            new Category("Category2", "Description2")
    );

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void allProductsJson() throws Exception {
        when(productService.getAllProducts(null))
                .thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[1].name").value("Product2"));
    }

   @Test
    void allProductsXml() throws Exception {
        when(productService.getAllProducts(null)).thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/List/item/name").string("Product1"))
                .andExpect(xpath("/List/item[2]/name").string("Product2"));
    }

    @Test
    void oneProductJson() throws Exception {
        product1.setId(1L);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product1));

        mockMvc.perform(get("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product1"));
    }

    @Test
    void oneProductXml() throws Exception {
        product1.setId(1L);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product1));

        mockMvc.perform(get("/products/1")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/product/id").string("1"))
                .andExpect(xpath("/product/name").string("Product1"));
    }

    @Test
    void createProductJson() throws Exception {
        Product createdProduct = product1;
        createdProduct.setId(1L);
        when(productService.createProduct(any(Product.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product1"));
    }

    @Test
    void createProductXml() throws Exception {
        Product createdProduct = product1;
        createdProduct.setId(1L);
        when(productService.createProduct(any(Product.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .content(xmlMapper.writeValueAsString(product1)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/product/id").string("1"))
                .andExpect(xpath("/product/name").string("Product1"));
    }

    @Test
    void updateProductJson() throws Exception {
        Product updatedProduct = product1;
        updatedProduct.setId(1L);
        updatedProduct.setName("UpdatedProduct");
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("UpdatedProduct"));
    }

    @Test
    void updateProductXml() throws Exception {
        Product updatedProduct = product1;
        updatedProduct.setId(1L);
        updatedProduct.setName("UpdatedProduct");
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .content(xmlMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/product/id").string("1"))
                .andExpect(xpath("/product/name").string("UpdatedProduct"));
    }


    @Test
    void updateProductWithCategoryJson() throws Exception {
        Product updatedProduct = product1;
        updatedProduct.setId(1L);
        updatedProduct.setName("UpdatedProduct");
        updatedProduct.setCategory(new Category("UpdatedCategory", "UpdatedDescription"));
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("UpdatedProduct"))
                .andExpect(jsonPath("$.category.name").value("UpdatedCategory"));
    }

    @Test
    void updateProductWithCategoryXml() throws Exception {
        Product updatedProduct = product1;
        updatedProduct.setId(1L);
        updatedProduct.setName("UpdatedProduct");
        updatedProduct.setCategory(new Category("UpdatedCategory", "UpdatedDescription"));
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .content(xmlMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(xmlMediaType))
                .andExpect(xpath("/product/id").string("1"))
                .andExpect(xpath("/product/name").string("UpdatedProduct"))
                .andExpect(xpath("/product/category/name").string("UpdatedCategory"));
    }

    @Test
    void deleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}