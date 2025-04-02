package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.CartItemDto;
import com.katatest.producttrial.model.CartItem;
import com.katatest.producttrial.repository.CartRepository;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.repository.UserRepository;
import com.katatest.producttrial.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CartItem addItem(CartItemDto cartItemDto) {
        if (!userRepository.existsById(cartItemDto.getUserId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if (!productRepository.existsById(cartItemDto.getProductId())) {
            throw new IllegalArgumentException("Product does not exist");
        }
        CartItem cartItem = new CartItem(cartItemDto);
        return cartRepository.save(cartItem);
    }

    @Override
    public void removeItem(String id) {
        Optional<CartItem> cartItem = cartRepository.findById(id);
        if (cartItem.isEmpty()) {
            throw new IllegalArgumentException("Cart item not found");
        }
        cartRepository.delete(cartItem.get());
    }

    @Override
    public CartItem updateItemQuantity(String id, CartItemDto cartItemDto) {
        Optional<CartItem> cartItemExist = cartRepository.findById(id);
        if (cartItemExist.isEmpty()) {
            throw new IllegalArgumentException("Cart item not found");
        }
        CartItem cartItem = cartItemExist.get();
        cartItem.setQuantity(cartItemDto.getQuantity());

        cartRepository.save(cartItem);
        return cartItem;
    }
}
