package com.qiroldev.monomarket.marketing.sale.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.marketing.sale.saletitle.SaleTitleEntity;
import com.qiroldev.monomarket.product.product.media.enums.MediaSize;
import com.qiroldev.monomarket.product.product.media.enums.MediaType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "sale_medias")
@RequiredArgsConstructor
public class SaleMediaEntity {

  private static final String GENERATOR_NAME = "sale_medias_gen";
  private static final String SEQUENCE_NAME = "sale_medias_seq";

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

  @ManyToOne()
  @JoinColumn(name = "sale_title_id")
  @JsonBackReference(value = "sale-medias")
  private SaleTitleEntity saleTitle;

  public SaleMediaEntity(
      String mediaKey,
      String url,
      MediaType type,
      String name,
      String savedName,
      MediaSize size,
      SaleTitleEntity newSaleTitle
  ) {
    this.mediaKey = mediaKey;
    this.url = url;
    this.type = type;
    this.name = name;
    this.savedName = savedName;
    this.size = size;
    this.saleTitle = newSaleTitle;
  }
}
