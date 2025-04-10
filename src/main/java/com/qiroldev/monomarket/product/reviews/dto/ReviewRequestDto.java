package com.qiroldev.monomarket.product.reviews.dto;

import com.qiroldev.monomarket.common.media.MediaRequestDto;
import java.util.List;
import lombok.Data;

@Data
public class ReviewRequestDto {

  private Long productId;

  private String comment;

  private List<MediaRequestDto> medias;

  private Double rating;
}
