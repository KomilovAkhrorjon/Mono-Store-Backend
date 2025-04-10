package com.qiroldev.monomarket.product.specification;


import com.qiroldev.monomarket.product.productspecification.ProductSpecificationEntity;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationResponseDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFilterParams;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationRequestDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationResponseDto;
import com.qiroldev.monomarket.product.specification.title.SpecificationTitleEntity;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecificationService {

  void createSpecification(
      List<SpecificationRequestDto> specificationRequestDto,
      Long categoryId,
      Principal principal);

  List<SpecificationTitleEntity> update(
      List<SpecificationRequestDto> specificationRequestDto,
      Long specificationId,
      Principal principal);

  Page<SpecificationResponseDto> getAllSpecifications(
      SpecificationFilterParams filterParams, Pageable pageable
  );

  SpecificationEntity getById(Long specificationId);

  List<SpecificationEntity> getByIds(List<Long> specificationIds);

  List<ProductSpecificationResponseDto> toDto(List<ProductSpecificationEntity> specifications);

  List<ProductSpecificationResponseDto> toDtoFromEntity(List<SpecificationEntity> specifications);

  List<SpecificationFullResponseDto> getFull(SpecificationFilterParams filterParams);
}
