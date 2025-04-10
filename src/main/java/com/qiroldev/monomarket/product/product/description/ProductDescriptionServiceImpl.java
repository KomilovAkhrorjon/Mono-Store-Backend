package com.qiroldev.monomarket.product.product.description;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.description.dto.ProductDescriptionDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

  private final ProductDescriptionRepository repository;

  @Override
  public void save(
      List<ProductDescriptionDto> descriptions,
      ProductEntity productEntity) {

    var descriptionsToSave = descriptions.stream()
        .map(description -> {
          var productDescription = new ProductDescriptionModel();
          productDescription.setId(description.getId() != null ?
              (description.getId() == 0 ? null : description.getId()) : null);
          productDescription.setDescription(description.getDescription());
          productDescription.setShortDescription(description.getShortDescription());
          productDescription.setLang(description.getLang());

          productDescription.setProduct(productEntity);
          return productDescription;
        })
        .toList();
    repository.saveAll(descriptionsToSave);

  }
}
