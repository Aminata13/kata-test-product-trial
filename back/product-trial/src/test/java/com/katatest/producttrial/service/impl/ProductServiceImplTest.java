package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.ProductDto;
import com.katatest.producttrial.model.Product;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.util.enums.InventoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testGetAll() {
        Product mockProduct1 = new Product("COD-01", "Shirt", "Cotton shirt", "image", "Clothes", 1000, 10, "REF-01", 7, InventoryStatus.INSTOCK, 4);
        Product mockProduct2 = new Product("COD-02", "Shirt", "Cotton shirt", "image", "Clothes", 1000, 10, "REF-01", 7, InventoryStatus.INSTOCK, 4);
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(mockProduct1);
        mockProducts.add(mockProduct2);

        Mockito.when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAll();
        assertNotNull(result);
        assertEquals(mockProducts.size(), result.size());
        assertEquals("COD-01", result.get(0).getCode());
        assertEquals("COD-02", result.get(1).getCode());
    }

    @Test
    void testGetById() {
        Product mockProduct = new Product("COD-01", "Shirt", "Cotton shirt", "image", "Clothes", 1000, 10, "REF-01", 7, InventoryStatus.INSTOCK, 4);
        Mockito.when(productRepository.findById("id")).thenReturn(Optional.of(mockProduct));

        Product result = productService.getById("id");
        assertNotNull(result);
        assertEquals(mockProduct.getId(), result.getId());
        assertEquals(mockProduct.getCode(), result.getCode());
        assertEquals(mockProduct.getName(), result.getName());
        assertEquals(mockProduct.getDescription(), result.getDescription());
        assertEquals(mockProduct.getPrice(), result.getPrice());
        assertEquals(mockProduct.getImage(), result.getImage());
        assertEquals(mockProduct.getCategory(), result.getCategory());
        assertEquals(mockProduct.getInventoryStatus(), result.getInventoryStatus());
        assertEquals(mockProduct.getQuantity(), result.getQuantity());
        assertEquals(mockProduct.getShellId(), result.getShellId());
        assertEquals(mockProduct.getInternalReference(), result.getInternalReference());
        assertEquals(mockProduct.getRating(), result.getRating());
    }

    @Test
    void testGetByIdWithException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> productService.getById("id"));
        assertTrue(illegalArgumentException.getMessage().contains("Product with id id not found"));
    }

    @Test
    void testAdd() {
        ProductDto mockProductDto = Mockito.mock(ProductDto.class);
        Product mockProduct = new Product(mockProductDto);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockProduct);

        Product result = productService.add(mockProductDto);
        assertNotNull(result);
        assertEquals(mockProductDto.getCode(), result.getCode());
        assertEquals(mockProductDto.getName(), result.getName());
    }

    @Test
    void testUpdate() {
        ProductDto mockProductDto = Mockito.mock(ProductDto.class);
        Product mockProduct = new Product("COD-01", "Shirt", "Cotton shirt", "image", "Clothes", 1000, 10, "REF-01", 7, InventoryStatus.INSTOCK, 4);
        Mockito.when(productRepository.findById("id")).thenReturn(Optional.of(mockProduct));

        Product result = productService.update("id", mockProductDto);
        assertNotNull(result);
        assertEquals(mockProductDto.getCode(), result.getCode());
    }

    @Test
    void testUpdateWithException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> productService.update("id", Mockito.mock(ProductDto.class)));
        assertTrue(illegalArgumentException.getMessage().contains("Product with id id not found"));
    }

    @Test
    void testDelete() {
        Mockito.when(productRepository.findById("id")).thenReturn(Optional.of(Mockito.mock(Product.class)));
        productService.delete("id");
    }

    @Test
    void testDeleteWithException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> productService.delete("id"));
        assertTrue(illegalArgumentException.getMessage().contains("Product with id id not found"));
    }
}