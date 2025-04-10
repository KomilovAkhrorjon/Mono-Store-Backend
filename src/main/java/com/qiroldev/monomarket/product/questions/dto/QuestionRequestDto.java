package com.qiroldev.monomarket.product.questions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionRequestDto {

  @NotNull
  private Long productId;

  @NotBlank
  private String question;
}
