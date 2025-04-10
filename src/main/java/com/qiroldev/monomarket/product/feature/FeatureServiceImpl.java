package com.qiroldev.monomarket.product.feature;

import com.qiroldev.monomarket.product.feature.dto.CreateFeatureRequestDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureFullResponseDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureResponseDto;
import com.qiroldev.monomarket.product.feature.title.FeatureTitleEntity;
import com.qiroldev.monomarket.product.feature.title.FeatureTitleRepository;
import com.qiroldev.monomarket.product.productfeature.ProductFeatureEntity;
import com.qiroldev.monomarket.product.productfeature.dto.ProductFeatureResponseDto;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeatureServiceImpl implements FeatureService {

  private final FeatureRepository repository;
  private final FeatureTitleRepository titleRepository;
  private final Utils utils;
  private final FeatureMapperImpl featureMapperImpl;

  @Override
  public void createFeature(
      CreateFeatureRequestDto createFeatureRequestDto,
      Principal principal) {

    var newFeature = repository.save(new FeatureEntity());

    var featureTitles = createFeatureRequestDto.getTitles();
    List<FeatureTitleEntity> titleEntities = new ArrayList<>();

    for (var title : featureTitles) {
      titleEntities.add(new FeatureTitleEntity(
          title.getTitle(),
          title.getLang(),
          title.getDescription(),
          newFeature
      ));
    }

    titleRepository.saveAll(titleEntities);
  }

  @Override
  public Page<FeatureResponseDto> getFeatures(Pageable pageable) {
    return repository.findAllWithDto(utils.getLang(), pageable);
  }

  @Override
  public void updateFeature(
      Long featureId,
      CreateFeatureRequestDto createFeatureRequestDto,
      Principal principal) {

    var feature = repository.findById(featureId).orElseThrow(
        () -> new ResourceNotFoundException("feature.not_found",
            utils.getMessage(Message.FEATURE_NOT_FOUND)
        )
    );

    var featureTitles = createFeatureRequestDto.getTitles();

    List<FeatureTitleEntity> titleEntities = new ArrayList<>();

    for (var title : featureTitles) {
      titleEntities.add(new FeatureTitleEntity(
          title.getId(),
          title.getTitle(),
          title.getLang(),
          title.getDescription(),
          feature
      ));
    }

    titleRepository.saveAll(titleEntities);
  }

  @Override
  public FeatureEntity getFeature(Long featureId) {
    return repository.findById(featureId).orElseThrow(
        () -> new ResourceNotFoundException("feature.not_found",
            utils.getMessage(Message.FEATURE_NOT_FOUND)
        )
    );
  }

  @Override
  public List<FeatureEntity> getFeaturesByIds(List<Long> featureIds) {

    if (featureIds != null) {
      return repository.findByIdIn(featureIds);
    } else {
      throw new ResourceNotFoundException("feature.not_found",
          utils.getMessage(Message.FEATURE_NOT_FOUND)
      );
    }
  }

  @Override
  public List<ProductFeatureResponseDto> getDtoList(List<ProductFeatureEntity> features) {
    var lang = utils.getLang();

    return features.stream()
        .map(feature -> {
          var f = feature.getFeature()
              .getTitles()
              .stream()
              .filter(title -> title.getLang().equals(lang))
              .findFirst()
              .orElse(new FeatureTitleEntity());
          return new ProductFeatureResponseDto(
              feature.getId(),
              f.getTitle(),
              f.getDescription()
          );
        })
        .toList();
  }

  @Override
  public Page<FeatureFullResponseDto> getFullFeatures(Pageable pageable) {
    return repository.findAll(pageable).map(featureMapperImpl::toFullResponseDto);
  }
}
