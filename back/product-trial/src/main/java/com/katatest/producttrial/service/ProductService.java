package com.katatest.producttrial.service;

import com.katatest.producttrial.dto.ProductDto;
import com.katatest.producttrial.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAll();
    public Product getById(String id);
    public Product add(ProductDto product);
    public Product update(String id, ProductDto product);
    public void delete(String id);
}
