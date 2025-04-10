package com.qiroldev.monomarket.marketing.sale.salesku.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class SaleSkuRequestDto {

  @NotNull
  private List<Long> skuId;

  @NotNull
  private Long saleId;

}
