package com.qiroldev.monomarket.product.productfeature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.feature.FeatureEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_features")
@RequiredArgsConstructor
public class ProductFeatureEntity {

  private static final String GENERATOR_NAME = "product_features_gen";
  private static final String SEQUENCE_NAME = "product_features_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonBackReference(value = "product-feature")
  private ProductEntity product;

  @ManyToOne
  @JoinColumn(name = "feature_id")
  private FeatureEntity feature;
}
