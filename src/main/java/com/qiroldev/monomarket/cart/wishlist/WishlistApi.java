package com.qiroldev.monomarket.cart.wishlist;

import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v0/wishlist")
public interface WishlistApi {

  @PutMapping()
  ResponseEntity<Void> addProductToWishlist(@RequestParam Long productId, Principal principal);

  @GetMapping()
  ResponseEntity<List<WishlistEntity>> getAll(Principal principal);

  @GetMapping("/count")
  ResponseEntity<Integer> countAllByUserUsername(Principal principal);

}
