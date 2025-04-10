package com.qiroldev.monomarket.product.gifttype;

import com.qiroldev.monomarket.product.gifttype.title.GiftTypeTitleModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gift_types")
@RequiredArgsConstructor
public class GiftTypeEntity {

  private static final String GENERATOR_NAME = "gift_types_gen";
  private static final String SEQUENCE_NAME = "gift_types_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "giftType")
  private List<GiftTypeTitleModel> titles;

  @Column(name = "type")
  private String type;

  @Column(name = "is_active")
  private Boolean isActive;

  public GiftTypeEntity(
      String type,
      Boolean isActive) {
    this.type = type;
    this.isActive = isActive;
  }
}
