package com.qiroldev.monomarket.product.product.attribute;

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
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attributes")
@RequiredArgsConstructor
public class AttributeEntity {

  private static final String GENERATOR_NAME = "attributes_gen";
  private static final String SEQUENCE_NAME = "attributes_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "description")
  private String description;

  @Column(name = "lang")
  @Enumerated(EnumType.STRING)
  private Lang lang;

  @ManyToOne()
  @JsonBackReference(value = "product-attribute")
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;


  public AttributeEntity(
      String description,
      Lang lang,
      LocalDateTime createdAt,
      ProductEntity product) {
    this.description = description;
    this.lang = lang;
    this.createdAt = createdAt;
    this.product = product;
  }
}
