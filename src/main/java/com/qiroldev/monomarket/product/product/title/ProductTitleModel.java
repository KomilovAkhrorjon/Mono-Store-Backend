package com.qiroldev.monomarket.product.product.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(
    name = "product_titles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "lang"})
)
public class ProductTitleModel {

  private final String GENERATOR_NAME = "product_titles_gen";
  private final String SEQUENCE_NAME = "product_titles_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "lang")
  @Enumerated(EnumType.STRING)
  private Lang lang;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonBackReference("product_titles")
  private ProductEntity product;

}
