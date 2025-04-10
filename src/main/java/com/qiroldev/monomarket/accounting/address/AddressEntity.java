package com.qiroldev.monomarket.accounting.address;

import com.qiroldev.monomarket.accounting.user.UserEntity;
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
@Table(name = "addresses")
@RequiredArgsConstructor
public class AddressEntity {

  private static final String GENERATOR_NAME = "addresses_gen";
  private static final String SEQUENCE_NAME = "addresses_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(name = "name")
  private String name;

  @Column(name = "note")
  private String note;

  @Column(name = "lat")
  private Double lat;

  @Column(name = "lon")
  private Double lon;

  public AddressEntity(String name, String note, Double lat, Double lon, UserEntity user) {
    this.name = name;
    this.note = note;
    this.lat = lat;
    this.lon = lon;
    this.user = user;
  }
}
