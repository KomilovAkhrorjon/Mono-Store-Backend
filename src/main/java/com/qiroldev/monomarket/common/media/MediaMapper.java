package com.qiroldev.monomarket.common.media;

import com.qiroldev.monomarket.homepage.slider.slidermedia.SliderMediaEntity;
import com.qiroldev.monomarket.product.product.media.MediaEntity;
import com.qiroldev.monomarket.product.reviews.reviewmedia.ReviewMediaEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        MediaMapper.class
    }
)
public interface MediaMapper {

  @Mapping(target = "id", source = "id")
  MediaResponseDto responseDto(SliderMediaEntity entity);


  @Mapping(target = "id", source = "id")
  MediaResponseDto responseDto(ReviewMediaEntity entity);

  List<MediaResponseDto> toDtoList(List<MediaEntity> mediaList);
}
