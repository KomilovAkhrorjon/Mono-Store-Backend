package com.qiroldev.monomarket.cart;

import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
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
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class CartEntity {

  private static final String GENERATOR_NAME = "carts_gen";
  private static final String SEQUENCE_NAME = "carts_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "sku_id")
  private SkuEntity sku;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @Column(name = "quantity")
  private Integer quantity;

}
