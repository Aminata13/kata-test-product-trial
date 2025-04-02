package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.CartItemDto;
import com.katatest.producttrial.model.CartItem;
import com.katatest.producttrial.repository.CartRepository;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceImplTest {

    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl(cartRepository, productRepository, userRepository);
    }

    @Test
    void testAddItem() {
        CartItemDto mockCartItemDto = new CartItemDto();
        mockCartItemDto.setProductId("id");
        mockCartItemDto.setUserId("id");
        CartItem mockCartItem = new CartItem(mockCartItemDto);
        Mockito.when(productRepository.existsById(mockCartItemDto.getProductId())).thenReturn(true);
        Mockito.when(userRepository.existsById(mockCartItemDto.getUserId())).thenReturn(true);
        Mockito.when(cartRepository.save(Mockito.any(CartItem.class))).thenReturn(mockCartItem);

        CartItem result = cartService.addItem(mockCartItemDto);
        Mockito.verify(cartRepository).save(Mockito.any(CartItem.class));
        assertNotNull(result);
        assertEquals(mockCartItemDto.getUserId(), result.getUserId());
        assertEquals(mockCartItemDto.getProductId(), result.getProductId());
    }

    @Test
    void testAddItem_UserNotFound() {
        CartItemDto mockCartItemDto = new CartItemDto();
        mockCartItemDto.setUserId("id");
        Mockito.when(userRepository.existsById("id")).thenReturn(false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.addItem(mockCartItemDto));
        assertEquals("User does not exist", exception.getMessage());
    }

    @Test
    void testAddItem_ProductNotFound() {
        CartItemDto mockCartItemDto = new CartItemDto();
        mockCartItemDto.setUserId("id");
        Mockito.when(userRepository.existsById("id")).thenReturn(true);
        Mockito.when(productRepository.existsById("id")).thenReturn(false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.addItem(mockCartItemDto));
        assertEquals("Product does not exist", exception.getMessage());
    }

    @Test
    void testRemoveItem() {
        Mockito.when(cartRepository.findById("id")).thenReturn(Optional.of(Mockito.mock(CartItem.class)));
        cartService.removeItem("id");
    }

    @Test
    void testRemoveItemWithException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.removeItem("id"));
        assertEquals("Cart item not found", exception.getMessage());
    }

    @Test
    void testUpdateItemQuantity() {
        CartItemDto mockCartItemDto = Mockito.mock(CartItemDto.class);
        CartItem mockCartItem = new CartItem("userId", "productId", 1);
        Mockito.when(cartRepository.findById("id")).thenReturn(Optional.of(mockCartItem));

        CartItem result = cartService.updateItemQuantity("id", mockCartItemDto);
        assertEquals(mockCartItemDto.getQuantity(), result.getQuantity());
    }

    @Test
    void testUpdateItemQuantityWithException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.updateItemQuantity("id", Mockito.mock(CartItemDto.class)));
        assertEquals("Cart item not found", exception.getMessage());
    }
}