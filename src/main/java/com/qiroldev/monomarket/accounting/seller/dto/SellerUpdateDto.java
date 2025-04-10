package com.qiroldev.monomarket.accounting.seller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SellerUpdateDto {

  @NotBlank
  String title;

  String descriptionUz;

  String descriptionRu;

  String descriptionEn;

  String logo;

  String banner;

  String phone;
}
