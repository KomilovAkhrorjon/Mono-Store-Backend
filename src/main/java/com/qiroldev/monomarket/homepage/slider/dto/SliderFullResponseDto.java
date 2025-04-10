package com.qiroldev.monomarket.homepage.slider.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import com.qiroldev.monomarket.homepage.slider.enums.SliderPlace;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SliderFullResponseDto {

  private Long id;

  private String color;

  private String link;

  private Boolean isActive;

  private Integer orderNo;

  private SliderPlace place;

  private LocalDateTime createdAt;

  private List<FullTitleResponseDto> titles;
}
