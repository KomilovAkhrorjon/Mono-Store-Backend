package com.qiroldev.monomarket.product.gifttype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiftTypeResponseDto {

  private Long id;

  private String type;

  private String title;

  private String subtitle;

}
