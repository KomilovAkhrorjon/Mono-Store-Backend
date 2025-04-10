package com.qiroldev.monomarket.product.reviews;

import com.qiroldev.monomarket.product.reviews.dto.ProductRatingResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

  @Query("SELECT r FROM ReviewEntity r WHERE r.product.id = :productId and r.disabled = false")
  Page<ReviewEntity> findAllByProductId(Long productId, Pageable pageable);

  @Query("""
      select new com.qiroldev.monomarket.product.reviews.dto.ProductRatingResponseDto(
      r.product.id,
      sum(r.rating),
      count(r.rating)
      )
      from ReviewEntity r
      where r.product.id in :#{#productId}
      group by r.product.id
      """)
  List<ProductRatingResponseDto> getAverageRatingByProductId(List<Long> productId);

  @Query("""
      select new com.qiroldev.monomarket.product.reviews.dto.ProductRatingResponseDto(
      r.product.id,
      sum(r.rating),
      count(r.rating)
      )
      from ReviewEntity r
      where r.product.id in :#{#productId}
      group by r.product.id
      """)
  ProductRatingResponseDto getAverageRatingByProductId(Long productId);
}
