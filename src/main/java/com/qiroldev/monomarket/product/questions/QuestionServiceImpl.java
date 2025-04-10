package com.qiroldev.monomarket.product.questions;

import com.qiroldev.monomarket.accounting.seller.SellerService;
import com.qiroldev.monomarket.accounting.seller.dto.MinimumSellerResponseDto;
import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.product.product.ProductService;
import com.qiroldev.monomarket.product.questions.dto.QuestionRequestDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionResponseDto;
import com.qiroldev.monomarket.product.questions.dto.QuestionUpdateDto;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final UserService userService;
  private final ProductService productService;
  private final SellerService sellerService;
  private final Utils utils;

  @Override
  public void create(QuestionRequestDto request, Principal principal) {
    var user = userService.getUser(principal);
    var product = productService.getProductById(request.getProductId());

    var question = new QuestionEntity();

    question.setProduct(product);
    question.setUser(user);
    question.setQuestion(request.getQuestion());

    questionRepository.save(question);

  }

  @Override
  public Page<QuestionResponseDto> getAllQuestions(Long productId, Pageable pageable) {
    var questions = questionRepository.findAllByProductIdAndDisabledIsFalse(productId, pageable);
    return questions
        .map(this::mapToDto);
  }

  private QuestionResponseDto mapToDto(QuestionEntity question) {
    return new QuestionResponseDto(
        question.getId(),
        question.getQuestion(),
        question.getAnswer(),
        question.getAnswerRate(),
        question.getCreatedAt(),
        new MinimumUserResponseDto(
            question.getUser().getId(),
            question.getUser().getFirstName(),
            question.getUser().getLastName()
        ),
        question.getSeller() != null ? new MinimumSellerResponseDto(
            question.getSeller().getId(),
            question.getSeller().getTitle(),
            question.getSeller().getLogo()
        ): null
    );
  }

  @Override
  public void update(QuestionUpdateDto request, Principal principal) {

    var seller = sellerService.getUserByUsername(principal.getName());

    var question = questionRepository.findById(request.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
                request.getId().toString(), List.of(utils.getMessage(Message.QUESTION_NOT_FOUND))
            )
        );

    question.setAnswer(request.getAnswer());
    question.setSeller(seller);
    question.setDisabled(request.getDisabled());
    question.setSeen(request.getSeen());

    questionRepository.save(question);
  }

  @Override
  public void rateAnswer(Long questionId, Double rate, Principal principal) {
    var question = questionRepository.findById(questionId)
        .orElseThrow(() -> new ResourceNotFoundException(
                questionId.toString(), List.of(utils.getMessage(Message.QUESTION_NOT_FOUND))
            )
        );

    if (question.getAnswer() == null){
      throw new ResourceNotFoundException(
          questionId.toString(), List.of(utils.getMessage(Message.QUESTION_NOT_ANSWERED))
      );
    }

    if (question.getSeen() == null){
      throw new ResourceNotFoundException(
          questionId.toString(), List.of(utils.getMessage(Message.QUESTION_NOT_SEEN))
      );
    }

    var seller = sellerService.getUserByUsername(principal.getName());

    if (!question.getProduct().getSeller().getId().equals(seller.getId())) {
      throw new ResourceNotFoundException(
          questionId.toString(), List.of(utils.getMessage(Message.ACCESS_DENIED))
      );
    }

    question.setAnswerRate(rate);
    question.setSeller(seller);

    questionRepository.save(question);
  }
}
