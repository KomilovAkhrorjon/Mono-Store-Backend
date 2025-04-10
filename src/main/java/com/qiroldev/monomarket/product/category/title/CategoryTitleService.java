package com.qiroldev.monomarket.product.category.title;

import java.util.List;

public interface CategoryTitleService {

  List<CategoryTitleModel> createCategoryTitle(List<CategoryTitleModel> categoryModel);

  List<CategoryTitleModel> updateCategoryTitle(List<CategoryTitleModel> categoryModel);

  void deleteCategoryTitleByCatalogId(Long catalogId);
}
