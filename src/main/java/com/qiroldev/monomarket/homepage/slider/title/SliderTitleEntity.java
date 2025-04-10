package com.qiroldev.monomarket.homepage.slider.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.homepage.slider.SliderEntity;
import com.qiroldev.monomarket.homepage.slider.slidermedia.SliderMediaEntity;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@ToString
@Entity
@Table(name = "slider_titles")
@RequiredArgsConstructor
public class SliderTitleEntity {

  private static final String GENERATOR_NAME = "slider_titles_gen";
  private static final String SEQUENCE_NAME = "slider_titles_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "lang")
  @Enumerated(EnumType.STRING)
  private Lang lang;

  @ManyToOne()
  @JoinColumn(name = "slider_id", referencedColumnName = "id")
  @JsonBackReference(value = "slider")
  private SliderEntity slider;

  @OneToMany(mappedBy = "sliderTitle")
  @Exclude
  private List<SliderMediaEntity> mediaList;
}
