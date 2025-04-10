package com.qiroldev.monomarket.product.specification.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecificationRequestDto {

  private Long id;

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotBlank
  private Lang lang;

}
