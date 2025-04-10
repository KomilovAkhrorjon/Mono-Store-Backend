package com.qiroldev.monomarket.product.product;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v0/product")
public interface ProductApi {

  @PostMapping()
  ResponseEntity<ProductFullDetailResponseDto> createProduct(
      @RequestBody @Valid ProductCreateRequestDto request,
      Principal principal
  );

  @GetMapping()
  ResponseEntity<Page<ProductListResponseDto>> getProduct(
      @RequestParam Long categoryId,
      Pageable pageable);

  @GetMapping("/all")
  ResponseEntity<List<ProductListResponseDto>> getAllProducts();

  @GetMapping("/{id}")
  ResponseEntity<ProductDetailResponseDto> getProductById(
      @PathVariable Long id);

  @GetMapping("/full/{id}")
  ResponseEntity<ProductFullDetailResponseDto> getFullProductById(
      @PathVariable Long id);

  @PutMapping()
  ResponseEntity<Void> updateProduct(
      @RequestBody @Valid ProductCreateRequestDto request,
      Principal principal
  );

  @PostMapping(
      value = "/upload",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  ResponseEntity<List<FileUploadResponseDto>> uploadProductImage(
      @RequestPart MultipartFile file
  );

  @PostMapping(
      value = "/upload/description",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  ResponseEntity<FileUploadResponseDto> uploadDescriptionImage(
      @RequestPart MultipartFile file
  );

  @PostMapping("/specification")
  ResponseEntity<ProductEntity> attachSpecification(
      @RequestBody @Valid ProductSpecificationCreateRequestDto requestDto,
      Principal principal);

  @PostMapping("/sku")
  ResponseEntity<Void> addSku(
      @RequestParam Long productId,
      @RequestBody @Valid List<SkuCreateRequestDto> skuCreateRequestDtos,
      Principal principal);

  @GetMapping("/sku/{id}")
  ResponseEntity<SkuPriceResponseDto> getSku(
      @PathVariable Long id);

  @PostMapping("/attach-feature")
  ResponseEntity<ProductEntity> attachFeature(
      @RequestBody @Valid ProductFeatureRequestDto requestDto,
      Principal principal);

  @PostMapping("/review")
  ResponseEntity<Void> attachReview(
      @RequestBody @Valid ReviewRequestDto request,
      Principal principal);

  @PatchMapping("/review")
  ResponseEntity<Void> updateReview(
      @RequestBody @Valid ReviewUpdateDto review,
      Principal principal
  );

  @GetMapping("/review")
  ResponseEntity<Page<ReviewResponseDto>> getReviews(
      @RequestParam Long productId,
      Pageable pageable);

  @PostMapping("/attach-to-gift")
  ResponseEntity<Void> attachToGift(
      @RequestBody List<Long> productId,
      @RequestParam Long giftCategoryId,
      Principal principal
  );

  @GetMapping("/home")
  ResponseEntity<List<ProductListResponseWithGiftCategoryDto>> getHomeProducts();

  @GetMapping("/full-gift-category")
  ResponseEntity<List<ProductFullDetailResponseDto>> getFullGiftCategoryProducts(
      @RequestParam Long giftCategoryId
  );

}
