package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFullResponseDto;
import com.qiroldev.monomarket.product.gifttype.GiftTypeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        GiftCategoryMapper.class,
        GiftTypeMapper.class
    }
)
public interface GiftCategoryMapper {

  GiftCategoryFullResponseDto toFullResponse(GiftCategoryEntity entity);

}
