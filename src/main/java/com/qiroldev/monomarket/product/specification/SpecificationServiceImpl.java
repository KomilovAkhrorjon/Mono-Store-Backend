package com.qiroldev.monomarket.product.specification;

import com.qiroldev.monomarket.product.productspecification.ProductSpecificationEntity;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.SpecificationCategoryService;
import com.qiroldev.monomarket.product.specifficationcategory.title.SpecificationCategoryTitleEntity;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFilterParams;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationRequestDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationResponseDto;
import com.qiroldev.monomarket.product.specification.title.SpecificationTitleEntity;
import com.qiroldev.monomarket.product.specification.title.SpecificationTitleRepository;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.exception.ForbiddenException;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecificationServiceImpl implements SpecificationService {

  private final SpecificationRepository repository;
  private final SpecificationTitleRepository titleRepository;
  private final SpecificationCategoryService categoryService;
  private final Utils utils;
  private final SpecificationMapper mapper;

  @Override
  public void createSpecification(
      List<SpecificationRequestDto> specificationRequestDto,
      Long categoryId,
      Principal principal) {

    var specificationList = new ArrayList<SpecificationTitleEntity>();

    var savedSpecificationCategory = categoryService.getById(categoryId);

    if (savedSpecificationCategory == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    var savedSpecification = repository.save(
        new SpecificationEntity(savedSpecificationCategory)
    );

    for (var dto : specificationRequestDto) {
      var specification = new SpecificationTitleEntity();
      specification.setTitle(dto.getTitle());
      specification.setDescription(dto.getDescription());
      specification.setLang(dto.getLang());
      specification.setSpecification(savedSpecification);
      specificationList.add(specification);
    }

    titleRepository.saveAll(specificationList);
  }

  @Override
  public List<SpecificationTitleEntity> update(
      List<SpecificationRequestDto> specificationRequestDto,
      Long specificationId,
      Principal principal) {

    var savedSpecification = repository.findById(specificationId)
        .orElseThrow(() -> new ResourceNotFoundException(
            utils.getMessage(Message.NOT_FOUND_EXCEPTION),
            List.of(specificationId.toString()).toString())
        );

    var specificationTitleList = new ArrayList<SpecificationTitleEntity>();

    for (var dto : specificationRequestDto) {
      try {
        var specificationTitle = titleRepository.findById(dto.getId())
            .orElseThrow();

        specificationTitle.setTitle(dto.getTitle());
        specificationTitle.setDescription(dto.getDescription());
        specificationTitle.setLang(dto.getLang());
        specificationTitle.setSpecification(savedSpecification);

        specificationTitleList.add(specificationTitle);
      } catch (ForbiddenException e) {
        specificationTitleList.add(new SpecificationTitleEntity(
            dto.getTitle(),
            dto.getDescription(),
            dto.getLang(),
            savedSpecification
        ));
      }
    }

    return titleRepository.saveAll(specificationTitleList);
  }

  @Override
  public Page<SpecificationResponseDto> getAllSpecifications(
      SpecificationFilterParams filterParams,
      Pageable pageable
  ) {
    return repository.findAllByFilterParams(filterParams, pageable).map(this::toDto);
  }

  @Override
  public SpecificationEntity getById(Long specificationId) {
    return repository.findById(specificationId)
        .orElseThrow(() -> new ResourceNotFoundException(
            utils.getMessage(Message.NOT_FOUND_EXCEPTION),
            List.of(specificationId.toString())));
  }

  @Override
  public List<SpecificationEntity> getByIds(List<Long> specificationIds) {
    return repository.findAllByIdIn(specificationIds);
  }

  @Override
  public List<ProductSpecificationResponseDto> toDto(
      List<ProductSpecificationEntity> specifications) {
    return toDtoFromEntity(
        specifications.stream().map(ProductSpecificationEntity::getSpecification).toList());
  }

  @Override
  public List<ProductSpecificationResponseDto> toDtoFromEntity(
      List<SpecificationEntity> specifications) {

    var lang = utils.getLang();

    return specifications.stream()
        .filter(specificationEntity -> specificationEntity.getCategory().getIsActive())
        .map(specification -> {
          var spec = specification.getTitles().stream()
              .filter(t -> t.getLang() == lang)
              .findFirst()
              .orElse(new SpecificationTitleEntity());
          var category = specification.getCategory()
              .getTitles().stream().filter(t -> t.getLang() == lang)
              .findFirst()
              .orElse(new SpecificationCategoryTitleEntity());
          return new ProductSpecificationResponseDto(
              specification.getId(),
              spec.getTitle(),
              spec.getDescription(),
              category.getId(),
              category.getTitle(),
              category.getDescription()
          );
        }).toList();
  }

  @Override
  public List<SpecificationFullResponseDto> getFull(SpecificationFilterParams filterParams) {
    return repository.findAllByFilterParams(filterParams)
        .stream()
        .map(mapper::toFullResponseDto)
        .toList();
  }

  private SpecificationResponseDto toDto(SpecificationEntity model) {
    var title = model.getTitles().stream().filter(
        t -> t.getLang() == utils.getLang()).findFirst().orElse(new SpecificationTitleEntity());
    return new SpecificationResponseDto(
        model.getId(),
        title.getTitle(),
        title.getDescription(),
        categoryService.toDto(model.getCategory())
    );
  }
}
