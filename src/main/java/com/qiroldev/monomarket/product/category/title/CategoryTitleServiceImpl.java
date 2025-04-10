package com.qiroldev.monomarket.product.category.title;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryTitleServiceImpl implements CategoryTitleService {

  private final CategoryTitleRepository repository;

  @Override
  public List<CategoryTitleModel> createCategoryTitle(List<CategoryTitleModel> categoryModel) {
    return repository.saveAll(categoryModel);
  }

  @Override
  public List<CategoryTitleModel> updateCategoryTitle(List<CategoryTitleModel> categoryModel) {
    return repository.saveAll(categoryModel);
  }

  @Override
  public void deleteCategoryTitleByCatalogId(Long catalogId) {
    repository.deleteAllByCategory_Id(catalogId);
  }
}
