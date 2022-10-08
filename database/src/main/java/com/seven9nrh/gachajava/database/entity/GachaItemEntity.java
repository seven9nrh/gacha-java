package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gacha_item")
@Data
public class GachaItemEntity implements Serializable {

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
