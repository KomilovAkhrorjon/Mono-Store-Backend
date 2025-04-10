package com.qiroldev.monomarket.homepage.slider.title.dto;

import com.qiroldev.monomarket.common.media.MediaRequestDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SliderTitleRequestDto {

  private Long id;

  @NotBlank
  private String title;

  @NotNull
  private Lang lang;

  private List<MediaRequestDto> mediaList;
}
