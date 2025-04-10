package com.qiroldev.monomarket.homepage.slider.dto;

import com.qiroldev.monomarket.common.media.MediaResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SliderResponseDto {

  private Long id;

  private String title;

  private String color;

  private String link;

  private List<MediaResponseDto> mediaList;
}
