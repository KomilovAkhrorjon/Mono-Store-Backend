package com.qiroldev.monomarket.product.gifttype.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.gifttype.GiftTypeEntity;
import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "gift_type_titles", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"gift_type_id", "lang"})
})
@NoArgsConstructor
public class GiftTypeTitleModel {

  private static final String GENERATOR_NAME = "gift_type_titles_gen";
  private static final String SEQUENCE_NAME = "gift_type_titles_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "subtitle")
  private String subtitle;

  @Column(name = "lang")
  private Lang lang;

  @ManyToOne
  @JoinColumn(name = "gift_type_id")
  @JsonBackReference(value = "giftType")
  private GiftTypeEntity giftType;

  public GiftTypeTitleModel(
      String title,
      String subtitle,
      Lang lang,
      GiftTypeEntity giftType) {
    this.title = title;
    this.subtitle = subtitle;
    this.lang = lang;
    this.giftType = giftType;
  }
}
