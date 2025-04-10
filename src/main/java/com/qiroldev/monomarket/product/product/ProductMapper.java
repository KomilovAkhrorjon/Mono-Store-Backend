package com.qiroldev.monomarket.product.product;


import com.qiroldev.monomarket.product.category.CategoryMapper;
import com.qiroldev.monomarket.product.product.dto.ProductFullDetailResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        ProductMapper.class,
        CategoryMapper.class
    }
)
public interface ProductMapper {

  @Mapping(target = "specifications", source = "specificationEntities")
  @Mapping(target = "features", source = "featureEntities")
  @Mapping(target = "skuList", ignore = true)
  ProductFullDetailResponseDto toProductFullDetailResponseDto(ProductEntity entity);

}
