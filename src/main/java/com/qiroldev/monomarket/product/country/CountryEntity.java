package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.product.country.title.CountryTitleModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "countries")
@RequiredArgsConstructor
public class CountryEntity {

  private static final String GENERATOR_NAME = "countries_gen";
  private static final String SEQUENCE_NAME = "countries_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "code")
  private String code;

  @OneToMany(mappedBy = "country")
  private List<CountryTitleModel> titles;

  public CountryEntity(String code) {
    this.code = code;
  }
}
