package com.qiroldev.monomarket.homepage.slider.title.dto;

import com.qiroldev.monomarket.common.media.MediaResponseDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SliderTitleResponseDto {

  private Long id;

  private String title;

  private Lang lang;

  List<MediaResponseDto> sliderMedias;
}
