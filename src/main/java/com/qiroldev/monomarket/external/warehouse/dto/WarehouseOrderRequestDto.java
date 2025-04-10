package com.qiroldev.monomarket.external.warehouse.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseOrderRequestDto {

  private String phone;

  private String name;

  private List<OrderProductRequestDto> skuList;

}
