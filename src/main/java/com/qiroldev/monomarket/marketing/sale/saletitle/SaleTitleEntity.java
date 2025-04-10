package com.qiroldev.monomarket.marketing.sale.saletitle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.marketing.sale.SaleEntity;
import com.qiroldev.monomarket.marketing.sale.media.SaleMediaEntity;
import com.qiroldev.monomarket.product.system.enums.Lang;
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
@Table(name = "sale_title")
  @RequiredArgsConstructor
public class SaleTitleEntity {

  private static final String GENERATOR_NAME = "sales_title_gen";
  private static final String SEQUENCE_NAME = "sales_title_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "lang")
  private Lang lang;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "saleTitle")
  private List<SaleMediaEntity> medias;

  @ManyToOne()
  @JsonBackReference(value = "sale-titles")
  @JoinColumn(name = "sale_id")
  private SaleEntity sale;

  public SaleTitleEntity(
      String title,
      String description,
      Lang lang,
      SaleEntity savedSale
  ) {
    this.title = title;
    this.description = description;
    this.lang = lang;
    this.sale = savedSale;
  }
}
