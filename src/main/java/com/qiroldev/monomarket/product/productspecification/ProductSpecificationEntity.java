package com.qiroldev.monomarket.product.productspecification;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_specifications")
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductSpecificationEntity {

  private static final String GENERATOR_NAME = "product_specifications_gen";
  private static final String SEQUENCE_NAME = "product_specifications_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonBackReference(value = "product-specification")
  private ProductEntity product;

  @ManyToOne
  @JoinColumn(name = "specification_id")
  private SpecificationEntity specification;

  public ProductSpecificationEntity(
      ProductEntity productEntity,
      SpecificationEntity specificationEntity
  ) {
    this.product = productEntity;
    this.specification = specificationEntity;
  }
}
