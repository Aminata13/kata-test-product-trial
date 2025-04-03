package com.katatest.producttrial.dto;

import com.katatest.producttrial.model.Product;

public class CartItemResponseDto {
    private Product product;
    private int quantity;

    public CartItemResponseDto() {
    }

    public CartItemResponseDto(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
