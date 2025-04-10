package com.qiroldev.monomarket.product.feature.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.feature.FeatureEntity;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feature_titles")
@RequiredArgsConstructor
@AllArgsConstructor
public class FeatureTitleEntity {

  private static final String GENERATOR_NAME = "feature_titles_gen";
  private static final String SEQUENCE_NAME = "feature_titles_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "lang")
  @Enumerated(EnumType.STRING)
  private Lang lang;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "feature_id")
  @JsonBackReference(value = "feature_titles")
  private FeatureEntity feature;

  public FeatureTitleEntity(
      String title,
      Lang lang,
      String description,
      FeatureEntity newFeature
  ) {
    this.title = title;
    this.lang = lang;
    this.feature = newFeature;
    this.description = description;
  }
}
