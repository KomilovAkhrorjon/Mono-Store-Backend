package com.qiroldev.monomarket.homepage.slider.slidermedia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.homepage.slider.title.SliderTitleEntity;
import com.qiroldev.monomarket.product.product.media.enums.MediaSize;
import com.qiroldev.monomarket.product.product.media.enums.MediaType;
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
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "slider_media")
@RequiredArgsConstructor
public class SliderMediaEntity {

  private static final String GENERATOR_NAME = "slider_media_gen";
  private static final String SEQUENCE_NAME = "slider_media_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "file_type")
  private String fileType;

  @Column(name = "url")
  private String url;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private MediaType type;

  @Column(name = "real_name")
  private String realName;

  @Column(name = "saved_name")
  private String savedName;

  @Column(name = "size")
  @Enumerated(EnumType.STRING)
  private MediaSize size;

  @ManyToOne()
  @JoinColumn(name = "slider_tite_id")
  @JsonBackReference(value = "sliderTitle")
  private SliderTitleEntity sliderTitle;

  public SliderMediaEntity(
      String fileType,
      String url,
      MediaType type,
      String realName,
      String savedName,
      MediaSize size,
      SliderTitleEntity title
  ) {
    this.fileType = fileType;
    this.url = url;
    this.type = type;
    this.realName = realName;
    this.savedName = savedName;
    this.size = size;
    this.sliderTitle = title;
  }

}
