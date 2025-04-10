package com.qiroldev.monomarket.product.reviews;

import com.qiroldev.monomarket.accounting.seller.SellerMapper;
import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.accounting.user.UserMapper;
import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.reviews.dto.ProductRatingResponseDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewRequestDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewResponseDto;
import com.qiroldev.monomarket.product.reviews.reviewmedia.ReviewMediaEntity;
import com.qiroldev.monomarket.product.reviews.reviewmedia.ReviewMediaRepository;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final ReviewMediaRepository reviewMediaRepository;
  private final Utils utils;
  private final MediaMapper mediaMapper;
  private final UserMapper userMapper;
  private final SellerMapper sellerMapper;

  @Override
  public void createReview(
      UserEntity user,
      ProductEntity product,
      ReviewRequestDto request
  ) {
    var review = reviewRepository.save(new ReviewEntity(
        request.getComment(),
        request.getRating(),
        user,
        product
    ));

    List<ReviewMediaEntity> medias = request.getMedias().stream()
        .map(media -> new ReviewMediaEntity(
            media.getFileType(),
            media.getUrl(),
            media.getType(),
            media.getRealName(),
            media.getSavedName(),
            media.getSize(),
            review
        ))
        .toList();

    reviewMediaRepository.saveAll(medias);

    reviewRepository.findById(review.getId()).orElseThrow(() ->
        new ResourceNotFoundException(review.getId().toString(), List.of(
            utils.getMessage(Message.REVIEW_NOT_FOUND)
        )
        )
    );
  }

  @Override
  public ReviewEntity update(ReviewEntity review) {
    return reviewRepository.save(review);
  }

  @Override
  public ReviewEntity getReview(Long id) {
    return reviewRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException(id.toString(), List.of(
            utils.getMessage(Message.REVIEW_NOT_FOUND)
        )
        )
    );
  }

  @Override
  public Page<ReviewResponseDto> findAllByProductId(Long productId, Pageable pageable) {
    return reviewRepository.findAllByProductId(productId, pageable)
        .map(this::castReviewEntityToDto);
  }

  private ReviewResponseDto castReviewEntityToDto(ReviewEntity review) {
    return new ReviewResponseDto(
        review.getId(),
        review.getRating(),
        review.getComment(),
        review.getRepliedComment(),
        review.getCreatedAt(),
        userMapper.minimumUserResponseDto(review.getUser()),
        review.getRepliedSeller() != null ?
            sellerMapper.minimumSellerResponseDto(review.getRepliedSeller()) : null,
        review.getMedias().stream()
            .map(mediaMapper::responseDto)
            .toList()
    );
  }

  @Override
  public Map<Long, Double> getAverageRatingByProductId(List<Long> productIdList) {
    return reviewRepository.getAverageRatingByProductId(productIdList)
        .stream().collect(
            Collectors.toMap(
                ProductRatingResponseDto::getProductId,
                ProductRatingResponseDto::getRating
            )
        );
  }

  @Override
  public ProductRatingResponseDto getAverageRatingByProductId(Long productId) {
    return reviewRepository.getAverageRatingByProductId(productId);
  }
}
