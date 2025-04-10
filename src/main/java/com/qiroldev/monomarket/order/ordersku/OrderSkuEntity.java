package com.qiroldev.monomarket.order.ordersku;

import com.qiroldev.monomarket.order.OrderEntity;
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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_skus")
@RequiredArgsConstructor
public class OrderSkuEntity {

  private static final String GENERATOR_NAME = "order_skus_gen";
  private static final String SEQUENCE_NAME = "order_skus_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "order_id")
  private OrderEntity order;

  @ManyToOne()
  @JoinColumn(name = "sku_id")
  private SkuEntity sku;

  @Column(name = "quantity")
  private Integer quantity;

  public OrderSkuEntity(
      OrderEntity savedOrder,
      SkuEntity sku,
      Integer quantity
  ) {
    this.order = savedOrder;
    this.sku = sku;
    this.quantity = quantity;
  }
}
