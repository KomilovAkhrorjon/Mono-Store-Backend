package com.qiroldev.monomarket.product.feature.title.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FeatureTitleDto {

  private Long id;

  @NotBlank
  private String title;

  @NotBlank
  private Lang lang;

  @NotBlank
  private String description;

}
