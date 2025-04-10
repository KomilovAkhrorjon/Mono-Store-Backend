package com.qiroldev.monomarket.product.product;

import com.qiroldev.monomarket.external.filestorage.FileStorageComponent;
import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import com.qiroldev.monomarket.external.warehouse.WarehouseComponent;
import com.qiroldev.monomarket.external.warehouse.dto.SkuPriceResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductCreateRequestDto;
import com.qiroldev.monomarket.product.product.dto.ProductDetailResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductFullDetailResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductListResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductListResponseWithGiftCategoryDto;
import com.qiroldev.monomarket.product.product.sku.dto.SkuCreateRequestDto;
import com.qiroldev.monomarket.product.productfeature.dto.ProductFeatureRequestDto;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationCreateRequestDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewRequestDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewResponseDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewUpdateDto;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProductController implements ProductApi{

  private final ProductService productService;
  private final FileStorageComponent fileStorageComponent;
  private final WarehouseComponent warehouseComponent;

  @Override
  public ResponseEntity<ProductFullDetailResponseDto> createProduct(
      @RequestBody @Valid ProductCreateRequestDto request,
      Principal principal
  ) {
     return ResponseEntity.ok(productService.createProduct(request, principal));
  }

  @Override
  public ResponseEntity<Page<ProductListResponseDto>> getProduct(
      Long categoryId,
      Pageable pageable) {
    return ResponseEntity.ok(productService.getAllProducts(categoryId, pageable));
  }

  @Override
  public ResponseEntity<List<ProductListResponseDto>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @Override
  public ResponseEntity<ProductDetailResponseDto> getProductById(Long id) {
    return ResponseEntity.ok(productService.getProductDetailsById(id));
  }

  @Override
  public ResponseEntity<ProductFullDetailResponseDto> getFullProductById(Long id) {
    return ResponseEntity.ok(productService.getFullProductDetailsById(id));
  }

  @Override
  public ResponseEntity<Void> updateProduct(ProductCreateRequestDto request, Principal principal) {
    productService.updateProduct(request, principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<List<FileUploadResponseDto>> uploadProductImage(MultipartFile file) {
    return ResponseEntity.ok(fileStorageComponent.uploadFile(file, FolderType.PRODUCT));
  }

  @Override
  public ResponseEntity<FileUploadResponseDto> uploadDescriptionImage(MultipartFile file) {
    return ResponseEntity.ok(fileStorageComponent.uploadSingle(file, FolderType.DESCRIPTION));
  }

  @Override
  public ResponseEntity<ProductEntity> attachSpecification(
      ProductSpecificationCreateRequestDto requestDto,
      Principal principal) {
    return ResponseEntity.ok(productService.attachSpecification(requestDto, principal));
  }

  @Override
  public ResponseEntity<Void> addSku(Long productId, List<SkuCreateRequestDto> skuCreateRequestDtos,
      Principal principal) {
    productService.addSku(productId, skuCreateRequestDtos, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<SkuPriceResponseDto> getSku(Long id) {
    return ResponseEntity.ok(warehouseComponent.getSkutPrice(id));
  }

  @Override
  public ResponseEntity<ProductEntity> attachFeature(ProductFeatureRequestDto requestDto,
      Principal principal) {
    return ResponseEntity.ok(productService.attachFeature(requestDto, principal));
  }

  @Override
  public ResponseEntity<Void> attachReview(ReviewRequestDto request, Principal principal) {
    productService.attachReview(request, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateReview(ReviewUpdateDto review, Principal principal) {
    productService.updateReview(review, principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Page<ReviewResponseDto>> getReviews(Long productId, Pageable pageable) {
    return ResponseEntity.ok(productService.getReviewsByProductId(productId, pageable));
  }

  @Override
  public ResponseEntity<Void> attachToGift(List<Long> productId, Long giftCategoryId,
      Principal principal) {
    productService.attachToGift(productId, giftCategoryId, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<ProductListResponseWithGiftCategoryDto>> getHomeProducts() {
    return ResponseEntity.ok(productService.homePromoProducts());
  }

  @Override
  public ResponseEntity<List<ProductFullDetailResponseDto>> getFullGiftCategoryProducts(
      Long giftCategoryId) {
    return ResponseEntity.ok(productService.getGiftCategoryProducts(giftCategoryId));
  }
}
