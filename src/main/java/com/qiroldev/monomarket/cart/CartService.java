package com.qiroldev.monomarket.cart;

import com.qiroldev.monomarket.cart.dto.AddToCartRequestDto;
import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import java.security.Principal;
import java.util.List;

public interface CartService {

  void addToUser(
      List<AddToCartRequestDto> skuList,
      Principal principal);

  List<CartResponseDto> getAll(Principal principal);

  List<CartEntity> getAllByPrincipal(Principal principal);

  void deleteAllByPrincipal(Principal principal);

  void delete(Long id, Principal principal);

  void createOrder(Principal principal);
}
