package com.example.assignment2_restful_ecommerce.product;

import com.example.assignment2_restful_ecommerce.PropertyMustBeUniqueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product1 = new Product(
                "Product1",
                "Description1",
                100.0,
                100,
                "image1.jpg",
                null
        );
    }

    @Test
    void getAllProducts_withKeyword_returnsFilteredProducts() {
        String keyword = "test";
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findByKeyword(keyword)).thenReturn(products);

        List<Product> result = productService.getAllProducts(keyword);

        assertEquals(products, result);
        verify(productRepository, times(1)).findByKeyword(keyword);
    }

    @Test
    void getAllProducts_withoutKeyword_returnsAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts(null);

        assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_existingId_returnsProduct() {
        Long id = 1L;
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(id);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void getProductById_nonExistingId_returnsEmpty() {
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById(id);

        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void saveProduct_validProduct_returnsSavedProduct() {
        when(productRepository.save(product1)).thenReturn(product1);

        Product result = productService.saveProduct(product1);

        assertEquals(product1, result);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    void saveProduct_duplicateProductName_throwsException() {
        product1.setName("duplicate");
        DataIntegrityViolationException exception = new DataIntegrityViolationException(
                "duplicate", new org.hibernate.exception.ConstraintViolationException("", null, "")
        );
        when(productRepository.save(product1)).thenThrow(exception);

        assertThrows(PropertyMustBeUniqueException.class, () -> productService.saveProduct(product1));
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    void updateProduct_existingId_updatesProduct() {
        Long id = 1L;
        Product existingProduct = product1;
        Product newProduct = product1;
        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product result = productService.updateProduct(id, newProduct);

        assertEquals(existingProduct, result);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void updateProduct_nonExistingId_throwsException() {
        Long id = 1L;
        Product newProduct = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(id, newProduct));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void deleteProduct_existingId_deletesProduct() {
        Long id = 1L;
        when(productRepository.existsById(id)).thenReturn(true);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteProduct_nonExistingId_throwsException() {
        Long id = 1L;
        when(productRepository.existsById(id)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(id));
        verify(productRepository, times(1)).existsById(id);
    }

    @Test
    void applyDiscount_validDiscount_appliesDiscount() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        int discount = 10;

        List<Product> result = productService.applyDiscount(products, discount);

        assertEquals(products.size(), result.size());
    }

    @Test
    void applyDiscount_invalidDiscount_throwsException() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        int discount = 110;

        assertThrows(IllegalArgumentException.class, () -> productService.applyDiscount(products, discount));
    }

    @Test
    void applyDiscountToProduct_validDiscount_appliesDiscount() {
        Product product = new Product();
        int discount = 10;

        Product result = productService.applyDiscount(product, discount);

        assertNotNull(result);
    }

    @Test
    void applyDiscountToProduct_invalidDiscount_throwsException() {
        Product product = new Product();
        int discount = 110;

        assertThrows(IllegalArgumentException.class, () -> productService.applyDiscount(product, discount));
    }
}