package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.common.mapper.FullTitleMapper;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        SpecificationCategoryMapper.class,
        FullTitleMapper.class
    }
)
public interface SpecificationCategoryMapper {

    SpecificationCategoryFullResponseDto toFullDto(SpecificationCategoryEntity entity);

}
