package com.qiroldev.monomarket.product.product.attribute.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttributeDto {

  private Long id;

  @NotBlank
  private String description;

  @NotBlank
  private Lang lang;
}
