package com.katatest.producttrial.service;

import com.katatest.producttrial.dto.CartItemDto;
import com.katatest.producttrial.model.CartItem;

public interface CartService {
    public CartItem addItem(CartItemDto cartItemDto);
    public void removeItem(String id);
    public CartItem updateItemQuantity(String id, CartItemDto cartItemDto);
}
