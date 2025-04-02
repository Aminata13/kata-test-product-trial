package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.WishListItemDto;
import com.katatest.producttrial.model.WishListItem;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.repository.UserRepository;
import com.katatest.producttrial.repository.WishListRepository;
import com.katatest.producttrial.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WishListItem addItem(WishListItemDto wishListItemDto) {
        if (!userRepository.existsById(wishListItemDto.getUserId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if (!productRepository.existsById(wishListItemDto.getProductId())) {
            throw new IllegalArgumentException("Product does not exist");
        }
        WishListItem wishListItem = new WishListItem(wishListItemDto);
        return wishListRepository.save(wishListItem);
    }

    @Override
    public void removeItem(String id) {
        Optional<WishListItem> wishListItem = wishListRepository.findById(id);
        if (wishListItem.isEmpty()) {
            throw new IllegalArgumentException("Wish list item not found");
        }
        wishListRepository.delete(wishListItem.get());
    }
}
