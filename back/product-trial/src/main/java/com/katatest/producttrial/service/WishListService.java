package com.katatest.producttrial.service;

import com.katatest.producttrial.dto.WishListItemDto;
import com.katatest.producttrial.model.WishListItem;

public interface WishListService {
    public WishListItem addItem(WishListItemDto wishListItemDto);
    public void removeItem(String id);
}
