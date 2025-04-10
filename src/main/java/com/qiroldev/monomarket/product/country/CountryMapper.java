package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.common.mapper.FullTitleMapper;
import com.qiroldev.monomarket.product.country.dto.CountryFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        CountryMapper.class,
        FullTitleMapper.class
    }
)
public interface CountryMapper {

  CountryFullResponseDto toFullResponseDto(CountryEntity country);

}
