package com.qiroldev.monomarket.product.productspecification;

import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {ProductSpecificationMapper.class}
)
public interface ProductSpecificationMapper {

  SpecificationFullResponseDto toSpecificationFullResponse(ProductSpecificationEntity productSpecifications);

}
