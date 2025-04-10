package com.qiroldev.monomarket.product.product.media.dto;

import com.qiroldev.monomarket.product.product.media.enums.MediaSize;
import com.qiroldev.monomarket.product.product.media.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductMediaCreateDto {

  @NotBlank
  private String mediaKey;

  @NotBlank
  private String url;

  @NotNull
  private MediaType type;

  @NotBlank
  private String name;

  @NotBlank
  private String savedName;

  @NotNull
  private MediaSize size;

  @NotNull
  private Integer orderNumber;

}
