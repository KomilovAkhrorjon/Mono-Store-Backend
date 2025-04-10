package com.qiroldev.monomarket.product.product.attribute;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.attribute.dto.AttributeDto;
import java.util.List;

public interface AttributeService {

  void saveList(List<AttributeDto> attributes, ProductEntity product);
}
