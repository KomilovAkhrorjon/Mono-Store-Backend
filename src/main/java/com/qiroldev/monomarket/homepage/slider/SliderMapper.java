package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.common.mapper.FullTitleMapper;
import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.homepage.slider.dto.SliderFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        SliderMapper.class,
        FullTitleMapper.class,
        MediaMapper.class
    }
)
public interface SliderMapper {

  @Mapping(target = "id", source = "id")
  SliderFullResponseDto toDto(SliderEntity slider);

}
