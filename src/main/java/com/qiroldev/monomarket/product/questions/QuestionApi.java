package com.qiroldev.monomarket.product.questions;

import com.qiroldev.monomarket.product.questions.dto.QuestionRequestDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionResponseDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionUpdateDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v0/questions")
public interface QuestionApi {

  @PostMapping()
  ResponseEntity<Void> create(@RequestBody QuestionRequestDto request, Principal principal);

  @GetMapping()
  ResponseEntity<Page<QuestionResponseDto>> getAllQuestions(
      @RequestParam Long productId,
      Pageable pageable);

  @PatchMapping("/rate-answer")
  ResponseEntity<Void> rateAnswer(
      @RequestParam Long questionId,
      @RequestParam Double rate,
      Principal principal);

  @PatchMapping()
  ResponseEntity<Void> update(@RequestBody QuestionUpdateDto request, Principal principal);
}
