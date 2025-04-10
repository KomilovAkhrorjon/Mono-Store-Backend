package com.qiroldev.monomarket.product.reviews.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewUpdateDto {

  @NotNull
  private Long id;

  private String repliedComment;

  @NotNull
  private Boolean seen;

  @NotNull
  private Boolean disabled;
}
