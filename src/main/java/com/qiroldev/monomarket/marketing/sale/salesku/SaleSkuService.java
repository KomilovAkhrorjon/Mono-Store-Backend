package com.qiroldev.monomarket.marketing.sale.salesku;

import com.qiroldev.monomarket.marketing.sale.SaleEntity;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import java.util.List;

public interface SaleSkuService {

  List<SaleSkuEntity> createSaleSku(List<SkuEntity> skulist, SaleEntity sale);
}
