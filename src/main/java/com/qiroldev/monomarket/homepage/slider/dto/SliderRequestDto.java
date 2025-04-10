package com.qiroldev.monomarket.homepage.slider.dto;

import com.qiroldev.monomarket.homepage.slider.enums.SliderPlace;
import com.qiroldev.monomarket.homepage.slider.title.dto.SliderTitleRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class SliderRequestDto {

  private Long id;

  @NotBlank
  private String color;

  @NotBlank
  private String link;

  @NotNull
  private Boolean isActive;

  @NotNull
  private SliderPlace place;

  @NotNull
  private Integer orderNo;

  @Size(max = 3, min = 3)
  private List<SliderTitleRequestDto> titles;


}
