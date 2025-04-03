package com.katatest.producttrial.repository;

import com.katatest.producttrial.model.WishListItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends MongoRepository<WishListItem, String> {
    List<WishListItem> findByUserId(String userId);
}
