package com.qiroldev.monomarket.product.category;

import com.qiroldev.monomarket.common.mapper.FullTitleMapper;
import com.qiroldev.monomarket.product.category.dto.CategoryFullParentDto;
import com.qiroldev.monomarket.product.category.dto.CategoryFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        CategoryMapper.class,
        FullTitleMapper.class
    }
)
public interface CategoryMapper {


  CategoryFullResponseDto toFullResponse(CategoryEntity category);

  CategoryFullParentDto map(CategoryEntity category);
}
