package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.homepage.slider.enums.SliderPlace;
import com.qiroldev.monomarket.homepage.slider.title.SliderTitleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@ToString
@Table(name = "sliders")
@RequiredArgsConstructor
public class SliderEntity {

  private static final String GENERATOR_NAME = "sliders_gen";
  private static final String SEQUENCE_NAME = "sliders_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "color")
  private String color;

  @Column(name = "link")
  private String link;

  @Column(name = "is_active", columnDefinition = "boolean default false")
  private Boolean isActive;

  @Column(name = "order_no")
  private Integer orderNo;

  @Column(name = "place")
  @Enumerated(EnumType.STRING)
  private SliderPlace place;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "slider")
  @Exclude
  private List<SliderTitleEntity> titles;

}
