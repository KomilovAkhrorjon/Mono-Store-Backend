package com.qiroldev.monomarket.product.questions;

import com.qiroldev.monomarket.product.questions.dto.QuestionRequestDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionResponseDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionUpdateDto;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionApi {

  private final QuestionService service;

  @Override
  public ResponseEntity<Void> create(QuestionRequestDto request,
      Principal principal) {
    service.create(request, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Page<QuestionResponseDto>> getAllQuestions(
      Long productId,
      Pageable pageable
  ) {
    return ResponseEntity.ok(service.getAllQuestions(productId, pageable));
  }

  @Override
  public ResponseEntity<Void> rateAnswer(Long questionId, Double rate, Principal principal) {
    service.rateAnswer(questionId, rate, principal);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> update(QuestionUpdateDto request, Principal principal) {
    service.update(request, principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
