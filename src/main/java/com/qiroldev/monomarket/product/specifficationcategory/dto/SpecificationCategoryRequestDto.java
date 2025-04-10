package com.qiroldev.monomarket.product.specifficationcategory.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecificationCategoryRequestDto {

  private Long id;

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  private Lang lang;

}
