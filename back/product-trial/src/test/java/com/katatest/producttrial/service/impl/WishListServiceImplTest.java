package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.WishListItemDto;
import com.katatest.producttrial.model.WishListItem;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.repository.UserRepository;
import com.katatest.producttrial.repository.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListServiceImplTest {

    private WishListServiceImpl wishListService;

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        wishListService = new WishListServiceImpl(wishListRepository, productRepository, userRepository);
    }

    @Test
    void testAddItem() {
        WishListItemDto mockWishListItemDto = new WishListItemDto();
        mockWishListItemDto.setUserId("id");
        mockWishListItemDto.setProductId("id");
        WishListItem mockWishListItem = new WishListItem(mockWishListItemDto);
        Mockito.when(userRepository.existsById("id")).thenReturn(true);
        Mockito.when(productRepository.existsById("id")).thenReturn(true);
        Mockito.when(wishListRepository.save(Mockito.any(WishListItem.class))).thenReturn(mockWishListItem);

        WishListItem result = wishListService.addItem(mockWishListItemDto);
        assertNotNull(result);
        assertEquals(mockWishListItemDto.getUserId(), result.getUserId());
    }

    @Test
    void testAddItem_UserNotFound() {
        WishListItemDto mockWishListItemDto = new WishListItemDto();
        mockWishListItemDto.setUserId("id");
        Mockito.when(userRepository.existsById("id")).thenReturn(false);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> wishListService.addItem(mockWishListItemDto));
        assertTrue(illegalArgumentException.getMessage().contains("User does not exist"));
    }

    @Test
    void testAddItem_ProductNotFound() {
        WishListItemDto mockWishListItemDto = new WishListItemDto();
        mockWishListItemDto.setUserId("id");
        mockWishListItemDto.setProductId("id");
        Mockito.when(userRepository.existsById("id")).thenReturn(true);
        Mockito.when(productRepository.existsById("id")).thenReturn(false);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> wishListService.addItem(mockWishListItemDto));
        assertTrue(illegalArgumentException.getMessage().contains("Product does not exist"));
    }

    @Test
    void testRemoveItem() {
        Mockito.when(wishListRepository.findById("id")).thenReturn(Optional.of(Mockito.mock(WishListItem.class)));
        wishListService.removeItem("id");
    }

    @Test
    void testRemoveItemWithException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> wishListService.removeItem(Mockito.any()));
        assertTrue(illegalArgumentException.getMessage().contains("Wish list item not found"));
    }
}