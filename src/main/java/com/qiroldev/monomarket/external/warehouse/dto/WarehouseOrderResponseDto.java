package com.qiroldev.monomarket.external.warehouse.dto;

import com.qiroldev.monomarket.external.warehouse.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class WarehouseOrderResponseDto {

  private Long id;

  private OrderStatus orderStatus;

  private OrderPaymentResponseDto payment;

  private LocalDateTime createdAt;

  private List<OrderProductResponseDto> orderProducts;

  private ClientResponseDto client;

}
