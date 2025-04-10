package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.product.giftcategory.title.GiftCategoryTitleModel;
import com.qiroldev.monomarket.product.giftproduct.GiftProductEntity;
import com.qiroldev.monomarket.product.gifttype.GiftTypeEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "gift_categories")
@RequiredArgsConstructor
public class GiftCategoryEntity {

  private static final String GENERATOR_NAME = "gift_categories_gen";
  private static final String SEQUENCE_NAME = "gift_categories_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne()
  @JoinColumn(name = "updated_by")
  private UserEntity updatedBy;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToOne()
  @JoinColumn(name = "deleted_by")
  private UserEntity deletedBy;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @ManyToOne()
  @JoinColumn(name = "gift_type_id")
  private GiftTypeEntity giftType;

  @Column(name = "link")
  private String link;

  @OneToMany(mappedBy = "giftCategory")
  private List<GiftCategoryTitleModel> titles;

  @OneToMany(mappedBy = "giftCategory")
  private List<GiftProductEntity> giftProducts;

  @Column(name = "order_no")
  private Integer orderNo;

  public GiftCategoryEntity(
      Boolean isActive,
      UserEntity user,
      GiftTypeEntity giftType,
      String link,
      Integer orderNo) {
    this.isActive = isActive;
    this.user = user;
    this.giftType = giftType;
    this.link = link;
    this.orderNo = orderNo;
  }

  public List<ProductEntity> getProducts() {
    return this.giftProducts.stream()
        .map(GiftProductEntity::getProduct)
        .toList();
  }
}
