package com.seven9nrh.gachajava.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "gacha_item")
@Data
@EqualsAndHashCode(callSuper = true)
public class GachaItemEntity extends AbstractEntity {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "gacha_item_name")
  private String name;

  @Column(name = "gacha_item_description")
  private String description;

  @Column(name = "gacha_item_rarity")
  private String rarity;
}
