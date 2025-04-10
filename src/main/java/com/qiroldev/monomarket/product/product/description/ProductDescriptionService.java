package com.qiroldev.monomarket.product.product.description;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.description.dto.ProductDescriptionDto;
import java.util.List;

public interface ProductDescriptionService {

  void save(List<ProductDescriptionDto> descriptionModel, ProductEntity productEntity);


}
