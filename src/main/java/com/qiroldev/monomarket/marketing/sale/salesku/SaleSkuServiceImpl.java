package com.qiroldev.monomarket.marketing.sale.salesku;

import com.qiroldev.monomarket.marketing.sale.SaleEntity;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleSkuServiceImpl implements SaleSkuService {

  private final SaleSkuRepository saleSkuRepository;

  @Override
  public List<SaleSkuEntity> createSaleSku(List<SkuEntity> skulist, SaleEntity sale) {

    for (var sku : skulist) {
      try {
        saleSkuRepository.save(
            new SaleSkuEntity(
                sku,
                sale
            )
        );
      }catch (Exception e){
        log.error("Error while saving sale sku {}", e.getMessage());
      }
    }

    return saleSkuRepository.findAllBySaleId(sale.getId());
  }
}
