package com.qiroldev.monomarket.product.category;

import com.qiroldev.monomarket.product.category.dto.CategoryCreateDto;
import com.qiroldev.monomarket.product.category.dto.CategoryFullResponseDto;
import com.qiroldev.monomarket.product.category.dto.CategoryResponseDto;
import com.qiroldev.monomarket.product.category.dto.ChildCategoryDto;
import com.qiroldev.monomarket.product.category.dto.ParentCategoryDto;
import com.qiroldev.monomarket.product.category.title.CategoryTitleModel;
import com.qiroldev.monomarket.product.category.title.CategoryTitleService;
import com.qiroldev.monomarket.product.category.title.dto.CategoryTitleRequestDto;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryTitleService titleService;
  private final Utils utils;
  private final CategoryMapper categoryMapper;

  @Override
  public void create(CategoryCreateDto request) {

    var category = new CategoryEntity(
        request.getIsPublic(),
        request.getIcon(),
        request.getPriority()
    );

    if (request.getParentId() != null) {
      var parent = categoryRepository.findById(request.getParentId());
      parent.ifPresent(category::setParent);
    }

    try {
      var categoryModel = categoryRepository.save(category);
      titleService.createCategoryTitle(castTitleDtoToModel(request.getTitles(), categoryModel));
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
  }

  @Override
  public CategoryResponseDto getOneLevelCategories(Long parentId) {

    var category = categoryRepository.findById(parentId)
        .orElseThrow(() -> new ResourceNotFoundException(
            utils.getMessage(Message.CATEGORY_NOT_FOUND),
            parentId.toString()
        ));
    var child = categoryRepository.oneLevelCategories(parentId);
    var parent = categoryRepository.getParentTree(parentId);
    return new CategoryResponseDto(
        category.getId(),
        getTitle(category.getTitles()),
        category.getIcon(),
        category.getPriority(),
        getParent(category, parent),
        getChild(child)
    );
  }

  @Override
  public void update(CategoryCreateDto request) {

    var category = categoryRepository.findById(request.getId())
        .orElseThrow(() -> new BadRequestException(utils.getMessage(Message.CATEGORY_NOT_FOUND)));

    if (request.getParentId() != null) {
      var parent = categoryRepository.findById(request.getParentId());
      parent.ifPresent(category::setParent);
    }

    titleService.updateCategoryTitle(castTitleDtoToModel(request.getTitles(), category));

    category.setIsPublic(request.getIsPublic());
    category.setIcon(request.getIcon());
    category.setPriority(request.getPriority());

    categoryRepository.save(category);
  }

  @Override
  public CategoryEntity getCategoryById(Long id) {
    return categoryRepository.findById(id).orElse(null);
  }

  @Override
  public List<CategoryResponseDto> getCategoriesTree() {

    var res = categoryRepository.findTree();

    return res.stream().map(
        category -> new CategoryResponseDto(
            category.getId(),
            getTitle(category.getTitles()),
            category.getIcon(),
            category.getPriority(),
            getParent(category, res),
            getChild(category.getChildren())
        )
    ).toList();
  }

  @Override
  public List<CategoryEntity> getCategoryTreeById(Long id) {
    return categoryRepository.getChildTree(id);
  }

  @Override
  public ParentCategoryDto getCategoryParent(CategoryEntity category) {

    var parent = categoryRepository.getParentTree(category.getId());

    return new ParentCategoryDto(
        category.getId(),
        getTitle(category.getTitles()),
        category.getIcon(),
        category.getPriority(),
        getParent(category, parent)
    );
  }

  @Override
  public List<CategoryFullResponseDto> getFullCategory() {
    return categoryRepository.findTree().stream().map(categoryMapper::toFullResponse).toList();
  }

  private ParentCategoryDto getParent(CategoryEntity category, List<CategoryEntity> res) {

    if (category.getParent() == null) {
      return null;
    }

    for (var c : res) {
      if (c.getId().equals(category.getParent().getId())) {
        return new ParentCategoryDto(
            c.getId(),
            getTitle(c.getTitles()),
            c.getIcon(),
            c.getPriority(),
            getParent(c, res)
        );
      }
    }

    return null;
  }

  private List<ChildCategoryDto> getChild(List<CategoryEntity> res) {
    if (res.isEmpty()) {
      return List.of();
    }
    return res.stream()
        .map(c -> new ChildCategoryDto(
            c.getId(),
            getTitle(c.getTitles()),
            c.getIcon(),
            c.getPriority(),
            getChild(c.getChildren())
        ))
        .toList();
  }

  private List<CategoryTitleModel> castTitleDtoToModel(
      List<CategoryTitleRequestDto> dtoList,
      CategoryEntity category) {
    return dtoList.stream()
        .map(dto -> new CategoryTitleModel(
            dto.getId(),
            dto.getTitle(),
            dto.getLang(),
            category
        ))
        .toList();
  }

  private String getTitle(List<CategoryTitleModel> titles) {
    for (var title : titles) {
      if (title.getLang().equals(utils.getLang())) {
        return title.getTitle();
      }
    }
    return "";
  }
}
