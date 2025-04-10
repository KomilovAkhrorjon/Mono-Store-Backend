package com.qiroldev.monomarket.product.reviews;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.accounting.seller.SellerModel;
import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.reviews.reviewmedia.ReviewMediaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
@Table(name = "reviews")
@RequiredArgsConstructor
public class ReviewEntity {

  private static final String GENERATOR_NAME = "reviews_gen";
  private static final String SEQUENCE_NAME = "reviews_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "rating")
  private Double rating;

  @Column(name = "comment")
  private String comment;

  @Column(name = "replied_comment")
  private String repliedComment;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "seen", columnDefinition = "boolean default false")
  private Boolean seen;

  @Column(name = "disabled", columnDefinition = "boolean default false")
  private Boolean disabled;

  @OneToMany(mappedBy = "review")
  private List<ReviewMediaEntity> medias;

  @ManyToOne()
  @JoinColumn(name = "product_id")
  @JsonBackReference(value = "product-reviews")
  private ProductEntity product;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne()
  @JoinColumn(name = "replied_seller_id")
  private SellerModel repliedSeller;

  public ReviewEntity(
      String comment,
      Double rating,
      UserEntity user,
      ProductEntity product
  ) {
    this.comment = comment;
    this.rating = rating;
    this.user = user;
    this.product = product;
  }

  @PrePersist
  public void prePersist() {

    if (this.seen == null) {
      this.seen = false;
    }

    if (this.disabled == null) {
      this.disabled = false;
    }
  }
}
