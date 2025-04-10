package com.qiroldev.monomarket.cart.dto;

import lombok.Data;

@Data
public class AddToCartRequestDto {

  private Long skuId;

  private Integer quantity;

}
