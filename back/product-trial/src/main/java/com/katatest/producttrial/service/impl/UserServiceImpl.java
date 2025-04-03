package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.CartItemResponseDto;
import com.katatest.producttrial.model.CartItem;
import com.katatest.producttrial.model.Product;
import com.katatest.producttrial.model.WishListItem;
import com.katatest.producttrial.repository.CartRepository;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.repository.WishListRepository;
import com.katatest.producttrial.service.UserService;
import com.katatest.producttrial.util.UserUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final CartRepository cartRepository;
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;

    public UserServiceImpl(CartRepository cartRepository, WishListRepository wishListRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CartItemResponseDto> getCart() {
        String userId = UserUtil.getCurrentUserId();
        List<CartItemResponseDto> cartItemResponseDtos = new ArrayList<>();
        List<CartItem> items = cartRepository.findByUserId(userId);
        for (CartItem item : items) {
            Optional<Product> productExist = productRepository.findById(item.getProductId());
            if (productExist.isPresent()) {
                Product product = productExist.get();
                CartItemResponseDto cartItemResponseDto = new CartItemResponseDto(product, item.getQuantity());
                cartItemResponseDtos.add(cartItemResponseDto);
            }
        }
        return cartItemResponseDtos;
    }

    @Override
    public List<Product> getWishList() {
        String userId = UserUtil.getCurrentUserId();
        List<Product> products = new ArrayList<>();
        List<WishListItem> items = wishListRepository.findByUserId(userId);
        for (WishListItem item : items) {
            Optional<Product> productExist = productRepository.findById(item.getProductId());
            productExist.ifPresent(products::add);
        }
        return products;
    }
}
