package com.qiroldev.monomarket.marketing.sale;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.marketing.sale.salesku.SaleSkuEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "sales")
@RequiredArgsConstructor
public class SaleEntity {
  private static final String GENERATOR_NAME = "sales_gen";
  private static final String SEQUENCE_NAME = "sales_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "color")
  private String color;

  @OneToMany(mappedBy = "sale")
  @JsonBackReference(value = "sale-sku")
  private List<SaleSkuEntity> skuList;


}
