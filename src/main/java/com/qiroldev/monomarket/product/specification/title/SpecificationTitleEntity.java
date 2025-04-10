package com.qiroldev.monomarket.product.specification.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
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
@Entity
@Table(name = "specification_titles")
@RequiredArgsConstructor
public class SpecificationTitleEntity {

  private static final String GENERATOR_NAME = "specification_titles_gen";
  private static final String SEQUENCE_NAME = "specification_titles_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "lang")
  @Enumerated(EnumType.STRING)
  private Lang lang;

  @ManyToOne
  @JoinColumn
  @JsonBackReference(value = "titles")
  private SpecificationEntity specification;

  public SpecificationTitleEntity(
      String title,
      String description,
      Lang lang,
      SpecificationEntity specification) {
    this.title = title;
    this.description = description;
    this.lang = lang;
    this.specification = specification;
  }

}
