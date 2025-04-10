package com.qiroldev.monomarket.order.dto;

import com.qiroldev.monomarket.external.warehouse.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDto {

  private Long id;

  private Double totalAmount;

  private Integer totalQuantity;

  private OrderStatus status;

  private LocalDateTime createdAt;

}
