package com.qiroldev.monomarket.accounting.seller;

import com.qiroldev.monomarket.accounting.seller.dto.MinimumSellerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        SellerMapper.class
    }
)
public interface SellerMapper {

  @Mapping(target = "id", source = "id")
  MinimumSellerResponseDto minimumSellerResponseDto(SellerModel seller);

}
