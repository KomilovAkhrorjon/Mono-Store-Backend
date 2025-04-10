package com.qiroldev.monomarket.product.product;

import com.qiroldev.monomarket.accounting.seller.SellerService;
import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.external.warehouse.WarehouseComponent;
import com.qiroldev.monomarket.external.warehouse.dto.ProductPriceResponseDto;
import com.qiroldev.monomarket.product.brand.BrandService;
import com.qiroldev.monomarket.product.category.CategoryEntity;
import com.qiroldev.monomarket.product.category.CategoryService;
import com.qiroldev.monomarket.product.country.CountryService;
import com.qiroldev.monomarket.product.feature.FeatureService;
import com.qiroldev.monomarket.product.giftcategory.GiftCategoryService;
import com.qiroldev.monomarket.product.giftproduct.GiftProductService;
import com.qiroldev.monomarket.product.product.attribute.AttributeEntity;
import com.qiroldev.monomarket.product.product.attribute.AttributeService;
import com.qiroldev.monomarket.product.product.description.ProductDescriptionModel;
import com.qiroldev.monomarket.product.product.description.ProductDescriptionService;
import com.qiroldev.monomarket.product.product.description.dto.ProductDescriptionResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductCreateRequestDto;
import com.qiroldev.monomarket.product.product.dto.ProductDetailResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductFilterParamsDto;
import com.qiroldev.monomarket.product.product.dto.ProductFullDetailResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductListResponseDto;
import com.qiroldev.monomarket.product.product.dto.ProductListResponseWithGiftCategoryDto;
import com.qiroldev.monomarket.product.product.media.MediaService;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import com.qiroldev.monomarket.product.product.sku.SkuService;
import com.qiroldev.monomarket.product.product.sku.dto.SkuCreateRequestDto;
import com.qiroldev.monomarket.product.product.sku.dto.SkuFullResponseDto;
import com.qiroldev.monomarket.product.product.sku.dto.SkuResponseDto;
import com.qiroldev.monomarket.product.product.sku.skuspecifications.SkuSpecificationsEntity;
import com.qiroldev.monomarket.product.product.title.ProductTitleModel;
import com.qiroldev.monomarket.product.product.title.ProductTitleService;
import com.qiroldev.monomarket.product.product.title.dto.ProductTitleDto;
import com.qiroldev.monomarket.product.productfeature.ProductFeatureService;
import com.qiroldev.monomarket.product.productfeature.dto.ProductFeatureRequestDto;
import com.qiroldev.monomarket.product.productspecification.ProductSpecificationService;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationCreateRequestDto;
import com.qiroldev.monomarket.product.reviews.ReviewService;
import com.qiroldev.monomarket.product.reviews.dto.ReviewRequestDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewResponseDto;
import com.qiroldev.monomarket.product.reviews.dto.ReviewUpdateDto;
import com.qiroldev.monomarket.product.specification.SpecificationService;
import com.qiroldev.monomarket.product.system.enums.Currency;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private final SellerService sellerService;
  private final CountryService countryService;
  private final BrandService brandService;
  private final AttributeService attributeService;
  private final ProductTitleService productTitleService;
  private final ProductSpecificationService productSpecificationService;
  private final Utils utils;
  private final SpecificationService specificationService;
  private final ProductFeatureService productFeatureService;
  private final FeatureService featureService;
  private final ReviewService reviewService;
  private final UserService userService;
  private final MediaMapper mediaMapper;
  private final WarehouseComponent warehouseComponent;
  private final SkuService skuService;
  private final MediaService mediaService;
  private final ProductDescriptionService descriptionService;
  private final ProductMapper productMapper;
  private final GiftCategoryService giftCategoryService;
  private final GiftProductService giftProductService;

  @Override
  @Transactional
  public ProductFullDetailResponseDto createProduct(
      ProductCreateRequestDto request,
      Principal principal) {

    var product = new ProductEntity();
    request.setId(null);

    return saveProduct(product, request);
  }

  @Override
  public Page<ProductListResponseDto> getAllProducts(
      Long categoryId,
      Pageable pageable) {

    var categoryIdList = getCategoyTreeIdlist(
        new ArrayList<>(categoryService.getCategoryById(categoryId).getChildren())
    );
    categoryIdList.add(categoryId);

    var products = productRepository.findAllByCategoryIdList(
        categoryIdList, pageable);

    var productIdList = products.stream()
        .map(ProductEntity::getId)
        .toList();

    var productRatings = reviewService.getAverageRatingByProductId(productIdList);

    var productWarehouseIdList = products.stream()
        .map(ProductEntity::getWarehouseId)
        .toList();

    var priceList = warehouseComponent.getProductPrices(productWarehouseIdList);

    return products.map(p -> productMapper(p, productRatings, priceList));
  }

  @Override
  public List<ProductListResponseDto> getAllProducts() {
    var products = productRepository.findAll();

    var productIdList = products.stream()
        .map(ProductEntity::getId)
        .toList();

    var productRatings = reviewService.getAverageRatingByProductId(productIdList);

    var productWarehouseIdList = products.stream()
        .map(ProductEntity::getWarehouseId)
        .toList();

    var priceList = warehouseComponent.getProductPrices(productWarehouseIdList);

    return products.stream().map(p -> {
      var product = new ProductListResponseDto();

      product.setId(p.getId());
      product.setWarehouseId(p.getWarehouseId());
      setTitle(product, p.getTitles());
      product.setBrand(brandService.getDto(p.getBrand()));
      product.setCategoryId(p.getCategory() != null ? p.getCategory().getId() : null);
      product.setMedias(mediaMapper.toDtoList(p.getMediaList()));
      setPrices(product, priceList);
      setRating(product, productRatings);

      return product;
    }).toList();
  }

  @Override
  public ProductEntity getProductById(Long id) {
    return productRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException(
            utils.getMessage(Message.PRODUCT_NOT_FOUND),
            List.of(id.toString())
        )
    );
  }

  @Override
  public ProductDetailResponseDto getProductDetailsById(Long id) {

    var lang = utils.getLang();

    var product = productRepository.findByIdAndStatusAndLang(
        id,
        ProductStatus.ACTIVE
    ).orElseThrow(() ->
        new ResourceNotFoundException(
            id.toString(),
            List.of(utils.getMessage(Message.PRODUCT_NOT_FOUND))
        )
    );

    var productDto = new ProductDetailResponseDto();

    productDto.setId(product.getId());
    productDto.setBrand(brandService.getDto(product.getBrand()));
    productDto.setCategory(
        categoryService.getCategoryParent(
            product.getCategory()
        )
    );
    setTitle(productDto, product.getTitles());
    productDto.setAttributes(
        product.getAttributes()
            .stream()
            .filter(attribute -> attribute.getLang().equals(lang))
            .map(AttributeEntity::getDescription)
            .toList()
    );
    productDto.setMedia(mediaMapper.toDtoList(product.getMediaList()));
    setDescription(productDto, product.getDescriptions());
    productDto.setRating(reviewService.getAverageRatingByProductId(product.getId()));
    productDto.setFeatures(featureService.getDtoList(product.getFeatures()));
    setSkus(productDto, product.getSkuList());
    productDto.setSpecifications(specificationService.toDto(product.getSpecifications()));

    return productDto;
  }

  @Override
  @Transactional
  public void updateProduct(ProductCreateRequestDto request, Principal principal) {

    var product = getProductById(request.getId());

    saveProduct(product, request);
  }

  @Transactional
  protected ProductFullDetailResponseDto saveProduct(ProductEntity product,
      ProductCreateRequestDto request) {
    var category = categoryService.getCategoryById(request.getCategoryId());

    product.setWarehouseId(request.getWarehouseId());
    product.setStatus(ProductStatus.ACTIVE);
    product.setModel(request.getModel());
    product.setCategory(category);
    product.setStatus(request.getStatus() != null ? request.getStatus() : ProductStatus.INACTIVE);

    if (request.getCountryId() != null) {
      var country = countryService.getCountryById(request.getCountryId());
      product.setCountry(country);
    } else {
      product.setCountry(null);
    }

    if (request.getBrandId() != null) {
      var brand = brandService.getBrandById(request.getBrandId());
      product.setBrand(brand);
    } else {
      product.setBrand(null);
    }

    var savedProduct = productRepository.save(product);

    attributeService.saveList(request.getAttributes(), savedProduct);
    productTitleService.createProductTitle(castTitleDtoToModel(request.getTitles(), savedProduct));
    descriptionService.save(request.getDescriptions(), savedProduct);
    productSpecificationService.attachAllSpecification(
        savedProduct,
        specificationService.getByIds(request.getSpecifications())
    );
    productFeatureService.attachProductFeatureToProduct(
        savedProduct,
        featureService.getFeaturesByIds(request.getFeatures())
    );
    mediaService.create(savedProduct, request.getMediaList());

    var productForCheck = productRepository.findById(savedProduct.getId()).orElseThrow(() ->
        new ResourceNotFoundException(
            savedProduct.getId().toString(),
            List.of(utils.getMessage(Message.PRODUCT_NOT_FOUND))
        )
    );

    skuService.prepareSkuListAfterAddingSpecifications(savedProduct);

    return getFullProductDetailsById(savedProduct.getId());
  }

  @Override
  public ResponseEntity<?> deleteProduct(Long id) {
    return null;
  }

  @Override
  public Boolean existsByFilter(ProductFilterParamsDto filterParams) {
    return productRepository.existsByFilter(filterParams);
  }

  @Override
  public ProductEntity attachSpecification(
      ProductSpecificationCreateRequestDto requestDto,
      Principal principal) {

    var product = productRepository.findById(requestDto.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException(
                requestDto.getProductId().toString(),
                List.of(utils.getMessage(
                        Message.PRODUCT_NOT_FOUND
                    )
                )
            )
        );

    var specification = specificationService.getById(requestDto.getSpecificationId());

    productSpecificationService.attachSpecification(product, specification);

    return productRepository.findById(requestDto.getProductId()).orElseThrow(() ->
        new ResourceNotFoundException(
            requestDto.getSpecificationId().toString(),
            List.of(utils.getMessage(Message.PRODUCT_NOT_FOUND))
        )
    );
  }

  @Override
  public ProductEntity attachFeature(ProductFeatureRequestDto requestDto, Principal principal) {

    var product = productRepository.findById(requestDto.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException(
                requestDto.getProductId().toString(),
                List.of(utils.getMessage(
                        Message.PRODUCT_NOT_FOUND
                    )
                )
            )
        );

    var features = featureService.getFeaturesByIds(requestDto.getFeatures());

    productFeatureService.attachProductFeatureToProduct(product, features);

    return productRepository.findById(
            requestDto.getProductId())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                requestDto.getProductId().toString(),
                List.of(utils.getMessage(Message.PRODUCT_NOT_FOUND)))
        );
  }

  @Override
  public void attachReview(ReviewRequestDto request, Principal principal) {

    var prod = getProductById(request.getProductId());
    var user = userService.getUser(principal);

    reviewService.createReview(user, prod, request);
  }

  @Override
  public void updateReview(ReviewUpdateDto review, Principal principal) {

    var seller = sellerService.getUserByUsername(principal.getName());
    var reviewEntity = reviewService.getReview(review.getId());

    reviewEntity.setRepliedComment(review.getRepliedComment());
    reviewEntity.setSeen(review.getSeen());
    reviewEntity.setDisabled(review.getDisabled());
    reviewEntity.setRepliedSeller(seller);

    reviewService.update(reviewEntity);
  }

  @Override
  public Page<ReviewResponseDto> getReviewsByProductId(Long productId, Pageable pageable) {
    return reviewService.findAllByProductId(productId, pageable);
  }

  @Override
  public void addSku(Long productId, List<SkuCreateRequestDto> skuCreateRequestDtos,
      Principal principal) {
    var product = getProductById(productId);
    skuService.createOrUpdate(product, skuCreateRequestDtos);
  }

  @Override
  public String getProductName(List<ProductTitleModel> titles) {
    return getTitle(titles);
  }

  @Override
  public ProductFullDetailResponseDto getFullProductDetailsById(Long id) {
    var product = getProductById(id);
    var response = productMapper.toProductFullDetailResponseDto(product);
    if (product.getSkuList() != null) {
      setSkus(response, product.getSkuList());
    }
    return response;
  }

  @Override
  public void attachToGift(List<Long> productId, Long giftCategoryId, Principal principal) {

    var products = productRepository.findAllByIdIn(productId);

    var giftCategory = giftCategoryService.getById(giftCategoryId);

    giftProductService.createOrUpdate(giftCategory, products);
  }

  @Override
  public List<ProductFullDetailResponseDto> getGiftCategoryProducts(Long giftCategoryId) {
    var giftCategory = giftCategoryService.getById(giftCategoryId);
    return giftCategory.getProducts()
        .stream()
        .map(productMapper::toProductFullDetailResponseDto)
        .toList();
  }

  @Override
  public List<ProductListResponseWithGiftCategoryDto> homePromoProducts() {

    var result = giftCategoryService.findAllByType("HOME");

    var productIdList = result.stream()
        .flatMap(giftCategory -> giftCategory.getProducts().stream())
        .map(ProductEntity::getId)
        .toList();

    var productRatings = reviewService.getAverageRatingByProductId(productIdList);

    var productWarehouseIdList = productRepository.findAllByIdIn(productIdList)
        .stream()
        .map(ProductEntity::getWarehouseId)
        .toList();

    var priceList = warehouseComponent.getProductPrices(productWarehouseIdList);

    List<ProductListResponseWithGiftCategoryDto> response = new ArrayList<>();

    for (var giftCategory : result) {
      var products = giftCategory.getProducts()
          .stream()
          .map(p -> productMapper(p, productRatings, priceList))
          .toList();

      response.add(new ProductListResponseWithGiftCategoryDto(
          giftCategoryService.toDto(giftCategory),
          products
      ));
    }

    return response;
  }

  // region Private methods

  private ProductListResponseDto productMapper(ProductEntity p,
      Map<Long, Double> productRatings,
      Map<Long, ProductPriceResponseDto> priceList) {
    var product = new ProductListResponseDto();

    product.setId(p.getId());
    setTitle(product, p.getTitles());
    product.setWarehouseId(p.getWarehouseId());
    product.setBrand(brandService.getDto(p.getBrand()));
    product.setCategoryId(p.getCategory().getId());
    product.setMedias(mediaMapper.toDtoList(p.getMediaList()));
    setPrices(product, priceList);
    setRating(product, productRatings);

    return product;
  }

  private List<ProductTitleModel> castTitleDtoToModel(
      List<ProductTitleDto> dtoToList,
      ProductEntity productEntity) {
    return dtoToList.stream()
        .map(c -> new ProductTitleModel(
            c.getId(),
            c.getTitle(),
            c.getLang(),
            productEntity
        ))
        .toList();
  }

  private void setRating(ProductListResponseDto product, Map<Long, Double> productRatings) {
    product.setRating(productRatings.get(product.getId()));
  }

  private void setTitle(ProductListResponseDto product, List<ProductTitleModel> titleModels) {
    product.setTitle(getTitle(titleModels));
  }

  private void setTitle(ProductDetailResponseDto product, List<ProductTitleModel> titleModels) {
    var lang = utils.getLang();
    product.setTitle(titleModels.stream()
        .filter(t -> t.getLang().equals(lang))
        .findFirst()
        .map(ProductTitleModel::getTitle)
        .orElse(""));
  }

  private void setPrices(
      ProductListResponseDto product,
      Map<Long, ProductPriceResponseDto> priceList
  ) {
    var price = priceList.get(product.getWarehouseId());
    if (price == null) {
      product.setActualPrice(0L);
      product.setDiscountPrice(0L);
      product.setCurrency(Currency.UZS);
      return;
    }
    product.setActualPrice(price.getPrice());
    product.setDiscountPrice(price.getDiscountPrice());
    product.setCurrency(price.getCurrency());
  }

  private void setDescription(ProductDetailResponseDto productDto,
      List<ProductDescriptionModel> descriptions) {
    var lang = utils.getLang();
    productDto.setDescription(descriptions.stream()
        .filter(d -> d.getLang().equals(lang))
        .findFirst()
        .map(
            d -> new ProductDescriptionResponseDto(
                d.getId(),
                d.getDescription(),
                d.getShortDescription()
            )
        )
        .orElse(new ProductDescriptionResponseDto())
    );
  }

  private void setSkus(ProductDetailResponseDto productDto, List<SkuEntity> skuList) {

    var skuIdList = skuList.stream()
        .map(SkuEntity::getWarehouseId)
        .toList();

    var skuPrices = warehouseComponent.getSkutPrices(skuIdList);

    productDto.setSkuList(
        skuList
            .stream()
            .map(sku -> {
              var price = skuPrices.get(sku.getWarehouseId());
              return new SkuResponseDto(
                  sku.getId(),
                  price.getSku(),
                  price.getPrice() != null ? price.getPrice() : 0,
                  price.getDiscountPrice() != null ? price.getDiscountPrice() : 0,
                  price.getAvailableQuantity() != null ? price.getAvailableQuantity() : 0,
                  sku.getSpecifications()
                      .stream()
                      .map(SkuSpecificationsEntity::getSpecificationId)
                      .toList()
              );
            })
            .toList());

  }

  private void setSkus(ProductFullDetailResponseDto productDto, List<SkuEntity> skuList) {

    var skuIdList = skuList.stream()
        .map(SkuEntity::getWarehouseId)
        .toList();

    var skuPrices = warehouseComponent.getSkutPrices(skuIdList);

    productDto.setSkuList(
        skuList
            .stream()
            .map(sku -> {
              var price = skuPrices.get(sku.getWarehouseId());
              return new SkuFullResponseDto(
                  sku.getId(),
                  price.getSku(),
                  price.getPrice(),
                  price.getDiscountPrice(),
                  price.getAvailableQuantity(),
                  sku.getSpecifications()
                      .stream()
                      .map(SkuSpecificationsEntity::getSpecificationId)
                      .toList(),
                  sku.getWarehouseId()
              );
            })
            .toList());

  }

  private String getTitle(List<ProductTitleModel> titles) {
    var lang = utils.getLang();
    return titles.stream()
        .filter(t -> t.getLang().equals(lang))
        .findFirst()
        .map(ProductTitleModel::getTitle)
        .orElse("");
  }

  private List<Long> getCategoyTreeIdlist(List<CategoryEntity> category) {
    List<Long> result = new ArrayList<>();

    for (var cat : category) {
      result.add(cat.getId());
      if (!cat.getChildren().isEmpty()) {
        result.addAll(getCategoyTreeIdlist(cat.getChildren()));
      }
    }

    return result;
  }

  // endregion
}

