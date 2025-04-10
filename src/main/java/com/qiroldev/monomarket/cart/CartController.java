package com.qiroldev.monomarket.cart;

import com.qiroldev.monomarket.cart.dto.AddToCartRequestDto;
import com.qiroldev.monomarket.cart.dto.CartResponseDto;
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
public class CartController implements CartApi {

  private final CartService service;

  @Override
  public ResponseEntity<Void> add(
      List<AddToCartRequestDto> skuList,
      Principal principal
  ) {
    service.addToUser(skuList, principal);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<List<CartResponseDto>> findAll(Principal principal) {
    return ResponseEntity.ok(service.getAll(principal));
  }

  @Override
  public ResponseEntity<Void> delete(Long id, Principal principal) {
    service.delete(id, principal);
    return ResponseEntity.accepted().build();
  }

  @Override
  public ResponseEntity<Void> createOrder(Principal principal) {
    service.createOrder(principal);
    return ResponseEntity.accepted().build();
  }
}
