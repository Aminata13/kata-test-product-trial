package com.katatest.producttrial.controller;

import com.katatest.producttrial.dto.WishListItemDto;
import com.katatest.producttrial.model.WishListItem;
import com.katatest.producttrial.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlists")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Wish List", description = "Contains all the operations that can be performed on a wish list : add or remove.")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @Operation(
            summary = "Add a product to a user wish list",
            description = "Add a WishListItemDto object by specifying the user id and the product id. The response is WishListItem object with the ids and the time of creation and update."
    )
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody WishListItemDto wishListItemDto) {
        WishListItem wishListItem = wishListService.addItem(wishListItemDto);
        return ResponseEntity.ok(wishListItem);
    }

    @Operation(
            summary = "Remove a product from a user wish list",
            description = "Remove by specifying the WishListItem id."
    )
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeItem(@PathVariable String id) {
        wishListService.removeItem(id);
        return ResponseEntity.ok("Product removed successfully from wish list");
    }
}
