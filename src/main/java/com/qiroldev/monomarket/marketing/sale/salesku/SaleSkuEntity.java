package com.qiroldev.monomarket.marketing.sale.salesku;

import com.qiroldev.monomarket.marketing.sale.SaleEntity;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sale_sku", uniqueConstraints = @UniqueConstraint(columnNames = {"sale_id", "sku_id"}))
@RequiredArgsConstructor
public class SaleSkuEntity {

  private static final String GENERATOR_NAME = "sale_sku_gen";
  private static final String SEQUENCE_NAME = "sale_sku_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "sale_id")
  private SaleEntity sale;

  @ManyToOne()
  @JoinColumn(name = "sku_id")
  private SkuEntity sku;

  public SaleSkuEntity(SkuEntity sku, SaleEntity sale) {
    this.sku = sku;
    this.sale = sale;
  }
}
