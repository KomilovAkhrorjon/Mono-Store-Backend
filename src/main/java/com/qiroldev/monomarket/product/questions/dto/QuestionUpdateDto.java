package com.qiroldev.monomarket.product.questions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionUpdateDto {

  @NotNull
  private Long id;

  private String answer;

  private Boolean seen;

  private Boolean disabled;

}
