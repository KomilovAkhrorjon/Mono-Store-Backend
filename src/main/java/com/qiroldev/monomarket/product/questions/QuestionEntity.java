package com.qiroldev.monomarket.product.questions;

import com.qiroldev.monomarket.accounting.seller.SellerModel;
import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "questions")
@RequiredArgsConstructor
public class QuestionEntity {

  private static final String GENERATOR_NAME = "questions_gen";
  private static final String SEQUENCE_NAME = "questions_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "question")
  private String question;

  @Column(name = "answer")
  private String answer;

  @Column(name = "answer_rate")
  private Double answerRate;

  @Column(name = "seen", columnDefinition = "boolean default false")
  private Boolean seen;

  @Column(name = "disabled",nullable = false, columnDefinition = "boolean default false")
  @ColumnDefault("false")
  private Boolean disabled;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne()
  @JoinColumn(name = "seller_id")
  private SellerModel seller;

  @ManyToOne()
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

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
