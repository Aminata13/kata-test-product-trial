package com.katatest.producttrial.controller;

import com.katatest.producttrial.dto.CartItemResponseDto;
import com.katatest.producttrial.model.Product;
import com.katatest.producttrial.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "Contains all the operations about users.")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get products from current user cart"
    )
    @GetMapping("/cart")
    public ResponseEntity<?> getProductsFromCart() {
        List<CartItemResponseDto> items = userService.getCart();
        return ResponseEntity.ok(items);
    }

    @Operation(
            summary = "Get products from current user wishlist"
    )
    @GetMapping("/wishList")
    public ResponseEntity<?> getProductsFromWishlist() {
        List<Product> products = userService.getWishList();
        return ResponseEntity.ok(products);
    }
}
