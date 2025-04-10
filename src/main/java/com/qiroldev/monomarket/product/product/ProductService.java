package com.qiroldev.monomarket.product.product;

import com.qiroldev.monomarket.product.product.dto.ProductCreateRequestDto;
import com.qiroldev.monomarket.product.product.dto.ProductFilterParamsDto;
import com.qiroldev.monomarket.product.product.dto.ProductDetailResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductFullDetailResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductListResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductListResponseWithGiftCategoryDto;
import com.qiroldev.monomarket.product.product.sku.dto.SkuCreateRequestDto;
import com.qiroldev.monomarket.product.product.title.ProductTitleModel;
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
import org.springframework.http.ResponseEntity;

public interface ProductService {

  ProductFullDetailResponseDto createProduct(@Valid ProductCreateRequestDto request, Principal principal);

  Page<ProductListResponseDto> getAllProducts(Long categoryId, Pageable pageable);

  List<ProductListResponseDto> getAllProducts();

  ProductEntity getProductById(Long id);

  ProductDetailResponseDto getProductDetailsById(Long id);

  void updateProduct(@Valid ProductCreateRequestDto request, Principal principal);

  ResponseEntity<?> deleteProduct(Long id);

  Boolean existsByFilter(ProductFilterParamsDto filterParams);

  ProductEntity attachSpecification(
      ProductSpecificationCreateRequestDto requestDto,
      Principal principal);

  ProductEntity attachFeature(ProductFeatureRequestDto requestDto, Principal principal);

  void attachReview(ReviewRequestDto request, Principal principal);

  void updateReview(ReviewUpdateDto review, Principal principal);

  Page<ReviewResponseDto> getReviewsByProductId(Long productId, Pageable pageable);

  void addSku(Long productId, List<SkuCreateRequestDto> skuCreateRequestDtos, Principal principal);

  String getProductName(List<ProductTitleModel> titles);

  ProductFullDetailResponseDto getFullProductDetailsById(Long id);

  void attachToGift(List<Long> productId, Long giftCategoryId, Principal principal);

  List<ProductFullDetailResponseDto> getGiftCategoryProducts(Long giftCategoryId);

  List<ProductListResponseWithGiftCategoryDto> homePromoProducts();

}
