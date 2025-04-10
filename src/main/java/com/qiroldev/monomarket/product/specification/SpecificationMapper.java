package com.qiroldev.monomarket.product.specification;

import com.qiroldev.monomarket.common.mapper.FullTitleMapper;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationResponseDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        SpecificationMapper.class,
        FullTitleMapper.class
    }
)
public interface SpecificationMapper {

  SpecificationFullResponseDto toFullResponseDto(SpecificationEntity entity);

  ProductSpecificationResponseDto toProductSpecificationResponseDto(SpecificationEntity entity);

}
