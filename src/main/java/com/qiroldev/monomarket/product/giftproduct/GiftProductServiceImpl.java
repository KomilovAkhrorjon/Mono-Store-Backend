package com.qiroldev.monomarket.product.giftproduct;

import com.qiroldev.monomarket.product.giftcategory.GiftCategoryEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftProductServiceImpl implements GiftProductService {

  private final GiftProductRepository repository;

  @Override
  public void createOrUpdate(GiftCategoryEntity giftCategory, List<ProductEntity> products) {
    log.info("createOrUpdate method called");

    var excitingProducts = repository.findAllByGiftCategoryId(giftCategory.getId());

    var noneMatchingProducts = excitingProducts.stream()
        .filter(product -> products.stream().noneMatch(p -> p.getId().equals(product.getProduct().getId())))
        .toList();

    if (!noneMatchingProducts.isEmpty()) {
      log.info("Deleting none matching products");
      repository.deleteAll(noneMatchingProducts);
    }

    var newProducts = products.stream()
        .filter(product -> excitingProducts
            .stream()
            .noneMatch(p -> p.getProduct().getId().equals(product.getId()))
        )
        .toList();

    List<GiftProductEntity> giftProducts = newProducts.stream()
        .map(product -> new GiftProductEntity(
            giftCategory,
            product
        ))
        .toList();

    if (!giftProducts.isEmpty()) {
      log.info("Saving new products");
      repository.saveAll(giftProducts);
    }
  }

  private List<ProductEntity> getProductsByCategory(Long giftCategoryId) {
    return repository.findAllByGiftCategoryId(giftCategoryId).stream()
        .map(GiftProductEntity::getProduct)
        .toList();
  }

}
