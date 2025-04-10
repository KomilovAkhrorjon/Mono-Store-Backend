package com.qiroldev.monomarket.product.specifficationcategory.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.specifficationcategory.SpecificationCategoryEntity;
import com.qiroldev.monomarket.product.system.enums.Lang;
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
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specification_category_titles", uniqueConstraints = {
    @UniqueConstraint(name = "specification_category_titles_title_lang_key", columnNames = {"title", "lang"})
})
@NoArgsConstructor
public class SpecificationCategoryTitleEntity {

  private static final String GENERATOR_NAME = "specification_category_titles_gen";
  private static final String SEQUENCE_NAME = "specification_category_titles_seq";

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
  @JsonBackReference("specificationCategoryTitles")
  private SpecificationCategoryEntity specificationCategory;

  public SpecificationCategoryTitleEntity(
      String title,
      String description,
      Lang lang,
      SpecificationCategoryEntity specificationCategory) {
    this.title = title;
    this.description = description;
    this.lang = lang;
    this.specificationCategory = specificationCategory;
  }
}
