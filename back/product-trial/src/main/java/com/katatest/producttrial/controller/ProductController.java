package com.katatest.producttrial.controller;

import com.katatest.producttrial.dto.ProductDto;
import com.katatest.producttrial.model.Product;
import com.katatest.producttrial.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Product", description = "Contains all the operations that can be performed on a product.")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Get all the products"
    )
    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Get one product"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(
            summary = "Create a product",
            description = "Create a product with a ProductDto Object containing the code, name, description..."
    )
    @PostMapping("")
    @PreAuthorize("authentication.name == 'admin@admin.com'")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        Product product = productService.add(productDto);
        return ResponseEntity.ok(product);
    }

    @Operation(
            summary = "Update a product",
            description = "Update a product by specifying its id and a ProductDto object with the changes"
    )
    @PatchMapping("/{id}")
    @PreAuthorize("authentication.name == 'admin@admin.com'")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        Product product = productService.update(id, productDto);
        return ResponseEntity.ok(product);
    }

    @Operation(
            summary = "Delete a product",
            description = "Product deletion by specifying its id"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.name == 'admin@admin.com'")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
