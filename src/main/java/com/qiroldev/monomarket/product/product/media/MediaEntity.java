package com.qiroldev.monomarket.product.product.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.media.enums.MediaSize;
import com.qiroldev.monomarket.product.product.media.enums.MediaType;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medias")
@RequiredArgsConstructor
public class MediaEntity {

  private static final String GENERATOR_NAME = "medias_gen";
  private static final String SEQUENCE_NAME = "medias_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "media_key")
  private String mediaKey;

  @Column(name = "url")
  private String url;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private MediaType type;

  @Column(name = "name")
  private String name;

  @Column(name = "saved_name")
  private String savedName;

  @Column(name = "size")
  @Enumerated(EnumType.STRING)
  private MediaSize size;

  @Column(name = "order_number")
  private Integer orderNumber;

  @ManyToOne()
  @JsonBackReference(value = "product-medias")
  private ProductEntity product;

  @ManyToOne()
  @JsonBackReference(value = "sku-medias")
  private SkuEntity sku;

  public MediaEntity(
      String mediaKey,
      String url,
      MediaType type,
      String name,
      String savedName,
      MediaSize size,
      Integer orderNumber,
      ProductEntity productEntity
  ) {
    this.mediaKey = mediaKey;
    this.url = url;
    this.type = type;
    this.name = name;
    this.savedName = savedName;
    this.size = size;
    this.orderNumber = orderNumber;
    this.product = productEntity;
  }
}
