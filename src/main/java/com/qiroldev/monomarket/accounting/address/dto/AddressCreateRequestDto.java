package com.qiroldev.monomarket.accounting.address.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressCreateRequestDto {

  private String name;
  private String note;
  private Double lat;
  private Double lon;

}
