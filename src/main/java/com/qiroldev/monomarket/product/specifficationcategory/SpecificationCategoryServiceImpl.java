package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.accounting.seller.SellerService;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFilterParams;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFullResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryRequestDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.title.SpecificationCategoryTitleEntity;
import com.qiroldev.monomarket.product.specifficationcategory.title.SpecificationCategoryTitleRepository;
import com.qiroldev.monomarket.utils.Utils;
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
public class SpecificationCategoryServiceImpl implements SpecificationCategoryService {

  private final SpecificationCategoryRepository repository;
  private final SpecificationCategoryTitleRepository titleRepository;
  private final SellerService sellerService;
  private final Utils utils;
  private final SpecificationCategoryMapper mapper;

  @Override
  public void createSpecificationCategory(
      List<SpecificationCategoryRequestDto> specificationCategoryDto,
      Principal principal) {

    var seller = sellerService.getUserByUsername(principal.getName());

    var savedSpecificationCategory = repository.save(
        new SpecificationCategoryEntity(seller, true));

    var specificationCategoryTitleList = new ArrayList<SpecificationCategoryTitleEntity>();

    for (var dto : specificationCategoryDto) {
      var specificationCategory = new SpecificationCategoryTitleEntity();
      specificationCategory.setTitle(dto.getTitle());
      specificationCategory.setDescription(dto.getDescription());
      specificationCategory.setLang(dto.getLang());
      specificationCategory.setSpecificationCategory(savedSpecificationCategory);
      specificationCategoryTitleList.add(specificationCategory);
    }

    titleRepository.saveAll(specificationCategoryTitleList);
  }

  @Override
  public void updateSpecificationCategory(
      List<SpecificationCategoryRequestDto> specificationCategoryDto,
      Boolean isActive,
      Long specificationCategoryId,
      Principal principal) {

    var seller = sellerService.getUserByUsername(principal.getName());

    var specificationCategory = repository.findByIdAndCreatedById(
        specificationCategoryId,
        seller.getId()
    ).orElseThrow(() -> new ResourceNotFoundException("",
        List.of(utils.getMessage(Message.NOT_FOUND_EXCEPTION)))
    );

    var specificationCategoryTitleList = new ArrayList<SpecificationCategoryTitleEntity>();

    for (var dto : specificationCategoryDto) {
      try {
        var specificationCategoryTitle = titleRepository.findById(dto.getId())
            .orElseThrow(() -> new Exception(utils.getMessage(Message.NOT_FOUND_EXCEPTION)));

        specificationCategoryTitle.setTitle(dto.getTitle());
        specificationCategoryTitle.setDescription(dto.getDescription());
        specificationCategoryTitle.setLang(dto.getLang());

        specificationCategoryTitleList.add(specificationCategoryTitle);
      } catch (Exception e) {

        specificationCategoryTitleList.add(new SpecificationCategoryTitleEntity(
                dto.getTitle(),
                dto.getDescription(),
                dto.getLang(),
                specificationCategory
            )
        );

      }
    }

    titleRepository.saveAll(specificationCategoryTitleList);

    specificationCategory.setIsActive(isActive);

    repository.save(specificationCategory);
  }

  @Override
  public Page<SpecificationCategoryResponseDto> getAllSpecificationCategories(
      SpecificationCategoryFilterParams filterParams, Pageable pageable) {
    return repository.findAllByFilterParams(filterParams, pageable).map(this::toDto);
  }

  @Override
  public SpecificationCategoryEntity getById(Long categoryId) {
    return repository.findById(categoryId).orElse(null);
  }

  @Override
  public SpecificationCategoryResponseDto toDto(SpecificationCategoryEntity category) {
    var title = category.getTitles().stream().filter(
            t -> t.getLang() == utils.getLang()).findFirst()
        .orElse(new SpecificationCategoryTitleEntity()
        );
    return new SpecificationCategoryResponseDto(
        category.getId(),
        title.getTitle(),
        title.getDescription()
    );
  }

  @Override
  public List<SpecificationCategoryFullResponseDto> getFull() {
    return repository.findAll().stream().map(mapper::toFullDto).toList();
  }
}
