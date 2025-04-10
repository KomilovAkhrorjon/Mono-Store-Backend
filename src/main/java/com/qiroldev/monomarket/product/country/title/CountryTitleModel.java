package com.qiroldev.monomarket.product.country.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.country.CountryEntity;
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
@Table(name = "country_titles", uniqueConstraints =
@UniqueConstraint(columnNames = {"title", "lang"})
)
@NoArgsConstructor
public class CountryTitleModel {

  private static final String GENERATOR_NAME = "country_titles_gen";
  private static final String SEQUENCE_NAME = "country_titles_seq";

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
  @JsonBackReference("country_titles")
  private CountryEntity country;

  public CountryTitleModel(
      CountryEntity country,
      String title,
      Lang lang) {
    this.country = country;
    this.title = title;
    this.lang = lang;
  }
}
