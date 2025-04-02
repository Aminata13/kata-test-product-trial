package com.katatest.producttrial.repository;

import com.katatest.producttrial.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<CartItem, String> {
}
