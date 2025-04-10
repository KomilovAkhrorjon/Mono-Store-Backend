package com.qiroldev.monomarket.order;

import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import com.qiroldev.monomarket.order.dto.OrderResponseDto;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v0/order")
public interface OrderApi {

  @GetMapping()
  ResponseEntity<List<OrderResponseDto>> getOrders(Principal principal);

  @GetMapping("/{id}")
  ResponseEntity<List<CartResponseDto>>  getOrderProducts(@PathVariable Long id, Principal principal);

}
