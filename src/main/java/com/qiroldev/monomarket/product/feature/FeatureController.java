package com.qiroldev.monomarket.product.feature;

import com.qiroldev.monomarket.product.feature.dto.CreateFeatureRequestDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureFullResponseDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureResponseDto;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FeatureController implements FeatureApi{

  private final FeatureService service;

  @Override
  public ResponseEntity<Void> createFeature(
      CreateFeatureRequestDto createFeatureRequestDto,
      Principal principal) {

    service.createFeature(createFeatureRequestDto, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateFeature(Long featureId,
      CreateFeatureRequestDto createFeatureRequestDto, Principal principal) {
    service.updateFeature(featureId, createFeatureRequestDto, principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Page<FeatureResponseDto>> getFeatures(Pageable pageable) {
    return ResponseEntity.ok(service.getFeatures(pageable));
  }

  @Override
  public ResponseEntity<Page<FeatureFullResponseDto>> getFullFeatures(Pageable pageable) {
    return ResponseEntity.ok(service.getFullFeatures(pageable));
  }
}
