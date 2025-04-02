package com.katatest.producttrial.dto;

public class WishListItemDto {

    private String userId;
    private String productId;

    public WishListItemDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
