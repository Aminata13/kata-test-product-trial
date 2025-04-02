package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.ProductDto;
import com.katatest.producttrial.model.Product;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(String id) {
        Optional<Product> productExist = productRepository.findById(id);
        if (productExist.isEmpty()) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }

        return productExist.get();
    }

    @Override
    public Product add(ProductDto productDto) {
        Product product = new Product(productDto);
        return productRepository.save(product);
    }

    @Override
    public Product update(String id, ProductDto productDto) {
        // Check first if product exists
        Optional<Product> productExist = productRepository.findById(id);

        if (productExist.isEmpty()) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }

        Product product = productExist.get();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setInternalReference(productDto.getInternalReference());
        product.setShellId(productDto.getShellId());
        product.setInventoryStatus(productDto.getInventoryStatus());
        product.setRating(productDto.getRating());

        productRepository.save(product);

        return product;
    }

    @Override
    public void delete(String id) {
        // Check first if product exists
        Optional<Product> productExist = productRepository.findById(id);

        if (productExist.isEmpty()) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }

        Product product = productExist.get();

        productRepository.delete(product);
    }
}
