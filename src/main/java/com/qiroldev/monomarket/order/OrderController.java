package com.qiroldev.monomarket.order;

import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import com.qiroldev.monomarket.order.dto.OrderResponseDto;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

  private final OrderService orderService;

  @Override
  public ResponseEntity<List<OrderResponseDto>> getOrders(Principal principal) {
    return ResponseEntity.ok(orderService.getOrders(principal));
  }

  @Override
  public ResponseEntity<List<CartResponseDto>> getOrderProducts(Long id, Principal principal) {
    return ResponseEntity.ok(orderService.getOrderProducts(id, principal));
  }
}
