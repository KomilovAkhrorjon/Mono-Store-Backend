package com.qiroldev.monomarket.cart.wishlist;

import com.qiroldev.monomarket.accounting.user.UserEntity;
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

@Getter
@Setter
@Entity
@Table(name = "wishlists")
@RequiredArgsConstructor
public class WishlistEntity {

  private static final String GENERATOR_NAME = "wishlists_gen";
  private static final String SEQUENCE_NAME = "wishlists_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(name = "is_public", columnDefinition = "boolean default false")
  private Boolean isPublic;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  public WishlistEntity(UserEntity user, ProductEntity product, boolean isPublic) {
    this.user = user;
    this.product = product;
    this.isPublic = isPublic;
  }
}
