package com.qiroldev.monomarket.product.product.sku.skuspecifications;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import jakarta.persistence.Column;
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
@Table(name = "sku_characteristics")
@RequiredArgsConstructor
public class SkuSpecificationsEntity {

  private static final String GENERATOR_NAME = "sku_characteristics_gen";
  private static final String SEQUENCE_NAME = "sku_characteristics_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "sku_id")
  @JsonBackReference(value = "sku-characteristics")
  private SkuEntity sku;

  @Column(name = "specification_id")
  private Long specificationId;

  public SkuSpecificationsEntity(
      SkuEntity newSku,
      Long specificationId
  ) {
    this.sku = newSku;
    this.specificationId = specificationId;
  }
}
