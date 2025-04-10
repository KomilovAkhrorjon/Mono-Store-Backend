package com.qiroldev.monomarket.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.accounting.address.AddressEntity;
import com.qiroldev.monomarket.accounting.user.UserEntity;
import com.qiroldev.monomarket.order.ordersku.OrderSkuEntity;
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
@Table(name = "orders")
@RequiredArgsConstructor
public class OrderEntity {

  private static final String GENERATOR_NAME = "orders_gen";
  private static final String SEQUENCE_NAME = "orders_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @OneToMany(mappedBy = "order")
  @JsonBackReference(value = "order-skus")
  private List<OrderSkuEntity> skus;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "total_discount")
  private Double totalDiscount;

  @Column(name = "used_promo_codes")
  private String usedPromoCodes;

  @Column(name = "discount_percentage")
  private Double discountPercentage;

  @ManyToOne()
  @JoinColumn(name = "address_id")
  private AddressEntity address;
}
