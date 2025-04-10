package com.qiroldev.monomarket.cart.wishlist;

import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WishlistController implements WishlistApi {

  private final WishlistService wishlistService;

  @Override
  public ResponseEntity<Void> addProductToWishlist(Long productId, Principal principal) {
    wishlistService.addProductToWishlist(productId, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<WishlistEntity>> getAll(Principal principal) {
    return ResponseEntity.ok(wishlistService.getAll(principal));
  }

  @Override
  public ResponseEntity<Integer> countAllByUserUsername(Principal principal) {
    return ResponseEntity.ok(wishlistService.getWishlistCount(principal));
  }


}
