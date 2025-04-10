package com.qiroldev.monomarket.product.gifttype;

import com.qiroldev.monomarket.common.mapper.FullTitleMapper;
import com.qiroldev.monomarket.product.category.CategoryMapper;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        CategoryMapper.class,
        FullTitleMapper.class
    }
)
public interface GiftTypeMapper {

  GiftTypeFullResponseDto toFullResponse(GiftTypeEntity entity);

}
