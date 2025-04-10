package com.qiroldev.monomarket.product.giftproduct;

import com.qiroldev.monomarket.product.giftcategory.GiftCategoryEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "gift_products")
@RequiredArgsConstructor
public class GiftProductEntity {

  private static final String GENERATOR_NAME = "gift_products_gen";
  private static final String SEQUENCE_NAME = "gift_products_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "gift_category_id")
  private GiftCategoryEntity giftCategory;

  @ManyToOne()
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public GiftProductEntity(GiftCategoryEntity giftCategory, ProductEntity product) {
    this.giftCategory = giftCategory;
    this.product = product;
  }
}
