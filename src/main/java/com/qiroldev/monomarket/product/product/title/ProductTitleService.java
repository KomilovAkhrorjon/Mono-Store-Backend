package com.qiroldev.monomarket.product.product.title;

import java.util.List;

public interface ProductTitleService {

  List<ProductTitleModel> createProductTitle(List<ProductTitleModel> productTitleModel);

  List<ProductTitleModel> updateProductTitle(List<ProductTitleModel> productModel);

  void deleteProductTitleByCatalogId(Long catalogId);

}
