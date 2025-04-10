package com.qiroldev.monomarket.cart;

import com.qiroldev.monomarket.cart.dto.AddToCartRequestDto;
import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v0/cart")
public interface CartApi {

  @PutMapping()
  ResponseEntity<Void> add(
      @RequestBody List<AddToCartRequestDto> skuList,
      Principal principal
  );

  @GetMapping()
  ResponseEntity<List<CartResponseDto>> findAll(Principal principal);

  @DeleteMapping()
  ResponseEntity<Void> delete(@RequestParam Long id, Principal principal);

  @PatchMapping()
  ResponseEntity<Void> createOrder(Principal principal);

}
