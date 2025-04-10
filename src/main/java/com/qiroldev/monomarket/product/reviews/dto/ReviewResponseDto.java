package com.qiroldev.monomarket.product.reviews.dto;

import com.qiroldev.monomarket.accounting.seller.dto.MinimumSellerResponseDto;
import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import com.qiroldev.monomarket.common.media.MediaResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponseDto {

  private Long id;

  private Double rating;

  private String comment;

  private String repliedComment;

  private LocalDateTime createdAt;

  private MinimumUserResponseDto user;

  private MinimumSellerResponseDto repliedSeller;

  private List<MediaResponseDto> medias;

}
