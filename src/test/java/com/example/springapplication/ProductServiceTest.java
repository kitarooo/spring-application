package com.example.springapplication;

import com.example.springapplication.entity.Category;
import com.example.springapplication.entity.Product;
import com.example.springapplication.repository.ProductRepository;
import com.example.springapplication.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    /**
     * Метод для тестирования логики сохранения продукта
     * */
    @Test
    void testSave() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setPrice(100.0);
        
        Category category = new Category();
        category.setId(1L);
        category.setCategory_name("test");
        category.setCategory_description("test");
        product.setCategory(category);
        when(productRepository.save(product)).thenReturn(product);


        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals("test", savedProduct.getName());
        assertEquals(100.0, savedProduct.getPrice());
        assertEquals(category, savedProduct.getCategory());
        verify(productRepository, times(1)).save(product);
    }

    /**
     * Метод для тестирования логики поиска продукта
     * */
    @Test
    void testПоискПоId() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.findById(productId);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
        assertEquals("Test Product", foundProduct.getName());
        verify(productRepository, times(1)).findById(productId);
    }
}
