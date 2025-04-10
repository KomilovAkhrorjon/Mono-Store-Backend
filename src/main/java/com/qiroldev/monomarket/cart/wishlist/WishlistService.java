package com.qiroldev.monomarket.cart.wishlist;

import java.security.Principal;
import java.util.List;

public interface WishlistService {

  void addProductToWishlist(Long productId, Principal principal);

  Integer getWishlistCount(Principal principal);

  List<WishlistEntity> getAll(Principal principal);
}
