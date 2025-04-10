package com.qiroldev.monomarket.external.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderProductRequestDto {

  private Long amount;

  private Long optionId;

}
