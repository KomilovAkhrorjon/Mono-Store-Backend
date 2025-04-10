package com.qiroldev.monomarket.marketing.promocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoCodeResultDto {

  private Double discount;

  private Double totalPercentage;
}
