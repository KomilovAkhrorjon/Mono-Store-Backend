package com.qiroldev.monomarket.product.giftcategory.title;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.giftcategory.GiftCategoryEntity;
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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gift_category_titles")
@RequiredArgsConstructor
public class GiftCategoryTitleModel {

  private static final String GENERATOR_NAME = "gift_category_titles_gen";
  private static final String SEQUENCE_NAME = "gift_category_titles_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "photo")
  private String photo;

  @Column(name = "banner")
  private String banner;

  @Column(name = "lang")
  private Lang lang;

  @ManyToOne
  @JsonBackReference(value = "giftCategory")
  @JoinColumn(name = "gift_category_id")
  private GiftCategoryEntity giftCategory;

  public GiftCategoryTitleModel(
      String title,
      String photo,
      String banner,
      Lang lang,
      GiftCategoryEntity giftCategory) {
    this.title = title;
    this.photo = photo;
    this.banner = banner;
    this.lang = lang;
    this.giftCategory = giftCategory;
  }
}
