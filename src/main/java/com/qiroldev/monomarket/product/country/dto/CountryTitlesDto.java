package com.qiroldev.monomarket.product.country.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import lombok.Data;

@Data
public class CountryTitlesDto {

  private Long id;
  private String title;
  private Lang lang;

}
