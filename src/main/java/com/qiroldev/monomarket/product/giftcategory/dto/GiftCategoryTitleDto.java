package com.qiroldev.monomarket.product.giftcategory.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GiftCategoryTitleDto {

  private Long id;

  @NotBlank
  private String title;

  private String photo;

  private String banner;

  private Lang lang;
}
