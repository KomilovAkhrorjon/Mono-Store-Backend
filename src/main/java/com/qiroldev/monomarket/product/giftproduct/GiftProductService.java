package com.qiroldev.monomarket.product.giftproduct;

import com.qiroldev.monomarket.product.giftcategory.GiftCategoryEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import java.util.List;

public interface GiftProductService {

  void createOrUpdate(GiftCategoryEntity giftCategory, List<ProductEntity> products);
}
