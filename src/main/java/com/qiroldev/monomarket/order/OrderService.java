package com.qiroldev.monomarket.order;

import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import com.qiroldev.monomarket.order.dto.CreateOrderRequestDto;
import com.qiroldev.monomarket.order.dto.OrderResponseDto;
import java.security.Principal;
import java.util.List;

public interface OrderService {

  void create(CreateOrderRequestDto requestDt, Principal principal);

  List<OrderResponseDto> getOrders(Principal principal);

  List<CartResponseDto> getOrderProducts(Long orderId, Principal principal);
}
