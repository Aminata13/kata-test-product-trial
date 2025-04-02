package com.katatest.producttrial.model;

import com.katatest.producttrial.dto.WishListItemDto;
import com.katatest.producttrial.model.abstracts.AuditMetaData;
import org.springframework.data.annotation.Id;

public class WishListItem extends AuditMetaData {

    @Id
    private String id;

    private String userId;
    private String productId;

    public WishListItem() {
    }

    public WishListItem(String id, String userId, String productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public WishListItem(WishListItemDto wishListItemDto) {
        this.userId = wishListItemDto.getUserId();
        this.productId = wishListItemDto.getProductId();
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
}
