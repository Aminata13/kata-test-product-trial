package com.katatest.producttrial.controller;

import com.katatest.producttrial.dto.CartItemDto;
import com.katatest.producttrial.model.CartItem;
import com.katatest.producttrial.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Cart", description = "Contains all the operations that can be performed on a user cart : add, remove or update quantity.")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(
            summary = "Add a product to a user cart",
            description = "Add a CartItemDto object by specifying the user id, the product id and the quantity. The response is CartItem object with the ids, the quantity and the time of creation and update."
    )
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = cartService.addItem(cartItemDto);
        return ResponseEntity.ok(cartItem);
    }

    @Operation(
            summary = "Remove a product from a user cart",
            description = "Remove by specifying the CartItem id."
    )
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeItem(@PathVariable String id) {
        cartService.removeItem(id);
        return ResponseEntity.ok("Product removed successfully from cart");
    }

    @Operation(
            summary = "Update the quantity of a product in a user cart",
            description = "Update a CartItemDto object by specifying its id, the user id, the product id and the new quantity. The response is CartItem object with the ids, the quantity saved and the time of creation and update."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateItemQuantity(@PathVariable String id, @RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = cartService.updateItemQuantity(id, cartItemDto);
        return ResponseEntity.ok(cartItem);
    }
}
