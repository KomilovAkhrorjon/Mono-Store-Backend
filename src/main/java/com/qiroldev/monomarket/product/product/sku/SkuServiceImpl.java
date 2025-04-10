package com.qiroldev.monomarket.product.product.sku;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.sku.dto.SkuCreateRequestDto;
import com.qiroldev.monomarket.product.product.sku.skuspecifications.SkuSpecificationsEntity;
import com.qiroldev.monomarket.product.product.sku.skuspecifications.SkuSpecificationsRepository;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService {

  private final SkuRepository skuRepository;
  private final SkuSpecificationsRepository skuSpecificationsRepository;


  @Override
  public void createOrUpdate(ProductEntity product,
      List<SkuCreateRequestDto> skuCreateRequestDtos) {

    var productSkus = skuRepository.findAllByProductId(product.getId())
        .stream().collect(Collectors.toMap(SkuEntity::getId, sku -> sku));

    for (var request : skuCreateRequestDtos) {
      SkuEntity newSku = new SkuEntity();

      if (request.getId() != null) {
        try {
          newSku = productSkus.get(request.getId());
        } catch (Exception e) {
          throw new ResourceNotFoundException("sku.not_found", List.of(request.getId().toString()));
        }

        if (!newSku.getProduct().getId().equals(product.getId())) {
          throw new ResourceNotFoundException("sku.not_found", List.of(request.getId().toString()));
        }
      }

      newSku.setWarehouseId(request.getWarehouseId());
      newSku.setProduct(product);

      SkuEntity finalNewSku = skuRepository.save(newSku);

      if (finalNewSku.getSpecifications() != null) {
        deleteSkuSpecifications(finalNewSku.getSpecifications());
      }

      skuSpecificationsRepository.saveAll(request.getSpecifications().stream().map(
          specificationId -> new SkuSpecificationsEntity(
              finalNewSku,
              specificationId
          )
      ).toList());
    }
  }

  private void deleteSkuSpecifications(List<SkuSpecificationsEntity> specifications) {
    skuSpecificationsRepository.deleteAll(specifications);
  }

  @Override
  public SkuEntity findById(Long skuId) {
    return skuRepository.findById(skuId)
        .orElseThrow(() ->
            new ResourceNotFoundException("sku.not_found", List.of(skuId.toString()))
        );
  }

  @Override
  public List<SkuEntity> findByIdIn(List<Long> skuIds) {
    return skuRepository.findByIdIn(skuIds);
  }

  @Override
  public void deleteSpecificationsFromSku(Long productId, List<Long> specificationIds) {
    var listToDelete = skuSpecificationsRepository.findAllBySpecificationIdInAndSkuProductId(
        specificationIds,
        productId);

    skuSpecificationsRepository.deleteAll(listToDelete);
  }

  @Override
  public void prepareSkuListAfterAddingSpecifications(ProductEntity savedProduct) {
    var skus = skuRepository.findAllByProductId(savedProduct.getId());
    for (var sku : skus) {
      if (sku.getSpecifications().isEmpty()) {
        skuRepository.delete(sku);
      }
    }
  }

  @Override
  public List<SkuEntity> findAllByWarehouseIdList(List<Long> warehouseIds) {
    return skuRepository.findAllByWarehouseIdIn(warehouseIds);
  }
}
