package com.qiroldev.monomarket.product.product.title;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductTitleServiceImpl implements ProductTitleService {

  private final ProductTitleRepository repository;

  @Override
  public List<ProductTitleModel> createProductTitle(List<ProductTitleModel> productTitleModel) {
    return repository.saveAll(productTitleModel);
  }

  @Override
  public List<ProductTitleModel> updateProductTitle(List<ProductTitleModel> productModel) {

    var idList = repository.getExitingTitleIdList(
        productModel.stream()
            .map(ProductTitleModel::getId)
            .toList()
    );

    var toUpdate = productModel.stream()
        .filter(c -> idList.contains(c.getId()))
        .toList();

    return repository.saveAll(toUpdate);
  }

  @Override
  public void deleteProductTitleByCatalogId(Long catalogId) {
    repository.deleteAllByProduct_Id(catalogId);
  }


}
