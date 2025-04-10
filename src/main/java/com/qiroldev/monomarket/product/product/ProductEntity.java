package com.qiroldev.monomarket.product.product;

import com.qiroldev.monomarket.accounting.seller.SellerModel;
import com.qiroldev.monomarket.product.brand.BrandEntity;
import com.qiroldev.monomarket.product.category.CategoryEntity;
import com.qiroldev.monomarket.product.country.CountryEntity;
import com.qiroldev.monomarket.product.feature.FeatureEntity;
import com.qiroldev.monomarket.product.product.attribute.AttributeEntity;
import com.qiroldev.monomarket.product.product.description.ProductDescriptionModel;
import com.qiroldev.monomarket.product.product.media.MediaEntity;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import com.qiroldev.monomarket.product.product.title.ProductTitleModel;
import com.qiroldev.monomarket.product.productfeature.ProductFeatureEntity;
import com.qiroldev.monomarket.product.productspecification.ProductSpecificationEntity;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "products")
@RequiredArgsConstructor
public class ProductEntity {

  private static final String GENERATOR_NAME = "products_gen";
  private static final String SEQUENCE_NAME = "products_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "product")
  @Column(name = "description", nullable = false)
  private List<ProductDescriptionModel> descriptions;

  @Column(name = "warehouse_id", nullable = false)
  private Long warehouseId;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ProductStatus status;

  @Column(name = "model")
  private String model;

  @OneToMany(mappedBy = "product")
  @Column(name = "title")
  private List<ProductTitleModel> titles;

  @ManyToOne()
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  @ManyToOne()
  @JoinColumn(name = "seller_id")
  private SellerModel seller;

  @ManyToOne()
  @JoinColumn(name = "country_id")
  private CountryEntity country;

  @ManyToOne()
  @JoinColumn(name = "brand_id")
  private BrandEntity brand;

  @OneToMany(mappedBy = "product")
  private List<AttributeEntity> attributes;

  @OneToMany(mappedBy = "product")
  private List<ProductSpecificationEntity> specifications;

  @OneToMany(mappedBy = "product")
  private List<ProductFeatureEntity> features;

  @OneToMany(mappedBy = "product")
  private List<SkuEntity> skuList;

  @OneToMany(mappedBy = "product")
  private List<MediaEntity> mediaList;

  @Column(name = "createdAt")
  @CreationTimestamp
  private LocalDateTime createdAt;

  public List<SpecificationEntity> getSpecificationEntities(){
    if (this.specifications == null) {
      return List.of();
    }
    return this.specifications.stream().map(ProductSpecificationEntity::getSpecification).toList();
  }

  public List<FeatureEntity> getFeatureEntities(){
    if (this.features == null) {
      return List.of();
    }
    return this.features.stream().map(ProductFeatureEntity::getFeature).toList();
  }
}
