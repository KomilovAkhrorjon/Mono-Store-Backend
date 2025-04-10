package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        BrandMapper.class,
        MediaMapper.class
    }
)
public interface BrandMapper {

  @Mapping(target = "id", source = "id")
  BrandResponseDto responseDto(BrandEntity entity);

}
