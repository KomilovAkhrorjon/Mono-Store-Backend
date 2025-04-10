package com.qiroldev.monomarket.product.feature;

import com.qiroldev.monomarket.product.feature.dto.FeatureFullResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        FeatureMapper.class
    }
)
public interface FeatureMapper {

    FeatureFullResponseDto toFullResponseDto(FeatureEntity feature);

}
