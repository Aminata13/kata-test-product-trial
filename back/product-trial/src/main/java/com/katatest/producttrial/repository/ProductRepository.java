package com.katatest.producttrial.repository;

import com.katatest.producttrial.model.Product;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsById(@NonNull String id);
    boolean existsByCode(String code);
}
