package com.qiroldev.monomarket.product.category;

import com.qiroldev.monomarket.product.category.dto.CategoryCreateDto;
import com.qiroldev.monomarket.product.category.dto.CategoryFullResponseDto;
import com.qiroldev.monomarket.product.category.dto.CategoryResponseDto;
import com.qiroldev.monomarket.product.category.dto.ParentCategoryDto;
import java.util.List;

public interface CategoryService {

  void create(CategoryCreateDto request);

  void update(CategoryCreateDto request);

  CategoryResponseDto getOneLevelCategories(Long parentId);

  CategoryEntity getCategoryById(Long id);

  List<CategoryResponseDto> getCategoriesTree();

  List<CategoryEntity> getCategoryTreeById(Long id);

  ParentCategoryDto getCategoryParent(CategoryEntity category);

  List<CategoryFullResponseDto> getFullCategory();
}
