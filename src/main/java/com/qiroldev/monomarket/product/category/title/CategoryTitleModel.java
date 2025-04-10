package com.qiroldev.monomarket.product.category.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.category.CategoryEntity;
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
    name = "category_titles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"category_id", "lang"})
)
public class CategoryTitleModel {

  private static final String GENERATOR_NAME = "category_titles_gen";
  private static final String SEQUENCE_NAME = "category_titles_seq";

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
  @JoinColumn(name = "category_id")
  @JsonBackReference(value = "category-titles")
  private CategoryEntity category;
}
