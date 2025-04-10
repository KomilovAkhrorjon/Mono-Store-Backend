package com.qiroldev.monomarket.product.questions;

import com.qiroldev.monomarket.product.questions.dto.QuestionRequestDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionResponseDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionUpdateDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

  void create(QuestionRequestDto request, Principal principal);

  Page<QuestionResponseDto> getAllQuestions(Long productId, Pageable pageable);

  void update(QuestionUpdateDto request, Principal principal);

  void rateAnswer(Long questionId, Double rate, Principal principal);
}
