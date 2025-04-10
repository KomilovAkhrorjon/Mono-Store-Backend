package com.qiroldev.monomarket.product.questions.dto;

import com.qiroldev.monomarket.accounting.seller.dto.MinimumSellerResponseDto;
import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponseDto {

  private Long id;

  private String question;

  private String answer;

  private Double answerRate;

  private LocalDateTime createdAt;

  private MinimumUserResponseDto user;

  private MinimumSellerResponseDto seller;

}
