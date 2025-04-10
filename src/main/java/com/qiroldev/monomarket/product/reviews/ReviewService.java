package com.qiroldev.monomarket.product.reviews;

import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.reviews.dto.ProductRatingResponseDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewRequestDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewResponseDto;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

  void createReview(
      UserEntity user,
      ProductEntity product,
      ReviewRequestDto request
  );

  ReviewEntity update(ReviewEntity review);

  ReviewEntity getReview(Long id);

  Page<ReviewResponseDto> findAllByProductId(Long productId, Pageable pageable);

  Map<Long, Double> getAverageRatingByProductId(List<Long> productIdList);

  ProductRatingResponseDto getAverageRatingByProductId(Long productId);
}
