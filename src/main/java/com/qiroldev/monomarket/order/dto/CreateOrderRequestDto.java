package com.qiroldev.monomarket.order.dto;

import lombok.Data;

@Data
public class CreateOrderRequestDto {

  private Long addressId;

  private String usedPromoCodes;
}
