package com.qiroldev.monomarket.product.feature;

import com.qiroldev.monomarket.product.feature.dto.CreateFeatureRequestDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureFullResponseDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureResponseDto;
import com.qiroldev.monomarket.product.productfeature.ProductFeatureEntity;
import com.qiroldev.monomarket.product.productfeature.dto.ProductFeatureResponseDto;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeatureService {

  void createFeature(CreateFeatureRequestDto createFeatureRequestDto, Principal principal);

  Page<FeatureResponseDto> getFeatures(Pageable pageable);

  void updateFeature(
      Long featureId,
      CreateFeatureRequestDto createFeatureRequestDto,
      Principal principal);

  FeatureEntity getFeature(Long featureId);

  List<FeatureEntity> getFeaturesByIds(List<Long> featureIds);

  List<ProductFeatureResponseDto> getDtoList(List<ProductFeatureEntity> features);

  Page<FeatureFullResponseDto> getFullFeatures(Pageable pageable);
}
