package com.qiroldev.monomarket.common.mapper;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import com.qiroldev.monomarket.homepage.slider.title.SliderTitleEntity;
import com.qiroldev.monomarket.product.category.title.CategoryTitleModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        FullTitleMapper.class
    }
)
public interface FullTitleMapper {

  List<FullTitleResponseDto> toDto(List<SliderTitleEntity> entity);

  List<FullTitleResponseDto> toCategoryDto(List<CategoryTitleModel> entity);

}
