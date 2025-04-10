package com.qiroldev.monomarket.product.brand.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandDto {

  Long id;

  @NotBlank
  String title;

  @NotBlank
  String logo;

  @NotBlank
  String titleLink;

  public BrandDto(
      Long id,
      String title,
      String logo,
      String titleLink) {
    this.id = id;
    this.title = title;
    this.logo = logo;
    this.titleLink = titleLink;
  }
}
