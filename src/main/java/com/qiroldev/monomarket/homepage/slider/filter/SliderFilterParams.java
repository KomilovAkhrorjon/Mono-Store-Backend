package com.qiroldev.monomarket.homepage.slider.filter;

import com.qiroldev.monomarket.homepage.slider.enums.SliderPlace;
import lombok.Data;

@Data
public class SliderFilterParams {

  private SliderPlace place;

  private Boolean isActive;
}
