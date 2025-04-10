package com.qiroldev.monomarket.product.product.description;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.system.enums.Lang;
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
@RequiredArgsConstructor
@Entity
@Table(name = "descriptions")
public class ProductDescriptionModel {

  private final String GENERATOR_NAME = "descriptions_gen";
  private final String SEQUENCE_NAME = "descriptions_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "description", columnDefinition = "text", nullable = false)
  private String description;

  @Column(name = "short_description", columnDefinition = "text", nullable = false)
  private String shortDescription;

  @Column(name = "lang", columnDefinition = "text", nullable = false)
  @Enumerated(EnumType.STRING)
  private Lang lang;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductEntity product;

}
