package com.qiroldev.monomarket.product.product.description.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDescriptionDto {

  private Long id;

  @NotBlank
  private String description;

  @NotBlank
  private String shortDescription;

  @NotBlank
  private Lang lang;

}
