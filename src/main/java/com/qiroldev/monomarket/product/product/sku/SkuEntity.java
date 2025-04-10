package com.qiroldev.monomarket.product.product.sku;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.media.MediaEntity;
import com.qiroldev.monomarket.product.product.sku.skuspecifications.SkuSpecificationsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skus")
@RequiredArgsConstructor
public class SkuEntity {

  private static final String GENERATOR_NAME = "skus_gen";
  private static final String SEQUENCE_NAME = "skus_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "warehouse_id")
  private Long warehouseId;

  @ManyToOne()
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @OneToMany(mappedBy = "sku")
  private List<SkuSpecificationsEntity> specifications;

  @OneToMany(mappedBy = "sku")
  private List<MediaEntity> media;

  public SkuEntity(
      Long warehouseId,
      ProductEntity product
  ) {
    this.warehouseId = warehouseId;
    this.product = product;
  }
}
