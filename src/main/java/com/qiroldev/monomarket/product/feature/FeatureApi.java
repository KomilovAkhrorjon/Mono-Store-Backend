package com.qiroldev.monomarket.product.feature;

import com.qiroldev.monomarket.product.feature.dto.CreateFeatureRequestDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureFullResponseDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureResponseDto;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v0/features")
public interface FeatureApi {

  @PostMapping()
  ResponseEntity<Void> createFeature(
      @RequestBody @Valid CreateFeatureRequestDto createFeatureRequestDto,
      Principal principal);

  @PutMapping("/{featureId}")
  ResponseEntity<Void> updateFeature(
      @PathVariable Long featureId,
      @RequestBody @Valid CreateFeatureRequestDto createFeatureRequestDto,
      Principal principal);

  @GetMapping()
  ResponseEntity<Page<FeatureResponseDto>> getFeatures(Pageable pageable);

  @GetMapping("/full")
  ResponseEntity<Page<FeatureFullResponseDto>> getFullFeatures(Pageable pageable);

}
