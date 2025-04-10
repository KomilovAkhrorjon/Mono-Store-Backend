package com.qiroldev.monomarket.cart.wishlist;

import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.product.product.ProductService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

  private final WishlistRepository wishlistRepository;
  private final ProductService productService;
  private final UserService userService;

  @Override
  public void addProductToWishlist(Long productId, Principal principal) {
    log.info("addProductToWishlist: productId={}, principal={}", productId, principal.getName());

    var user = userService.getUser(principal);
    var product = productService.getProductById(productId);

    wishlistRepository.save(new WishlistEntity(
        user,
        product,
        false
    ));
  }

  @Override
  public Integer getWishlistCount(Principal principal) {
    return wishlistRepository.countAllByUserUsername(principal.getName());
  }

  @Override
  public List<WishlistEntity> getAll(Principal principal) {
    return wishlistRepository.findAllByUserUsername(principal.getName());
  }
}
