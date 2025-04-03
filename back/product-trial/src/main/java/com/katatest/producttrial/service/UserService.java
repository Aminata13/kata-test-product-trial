package com.katatest.producttrial.service;

import com.katatest.producttrial.dto.CartItemResponseDto;
import com.katatest.producttrial.model.Product;

import java.util.List;

public interface UserService {
    public List<CartItemResponseDto> getCart();
    public List<Product> getWishList();
}
