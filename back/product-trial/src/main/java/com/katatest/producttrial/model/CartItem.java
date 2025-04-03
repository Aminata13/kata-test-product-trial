package com.katatest.producttrial.model;

import com.katatest.producttrial.dto.CartItemDto;
import com.katatest.producttrial.model.abstracts.AuditMetaData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cartItems")
public class CartItem extends AuditMetaData {

    @Id
    private String id;

    private String userId;
    private String productId;
    private int quantity;

    public CartItem() {}

    public CartItem(String userId, String productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem(CartItemDto cartItemDto) {
        this.userId = cartItemDto.getUserId();
        this.productId = cartItemDto.getProductId();
        this.quantity = cartItemDto.getQuantity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
