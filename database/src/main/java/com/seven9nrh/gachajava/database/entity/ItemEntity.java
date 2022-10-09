package com.seven9nrh.gachajava.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "item")
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemEntity extends AbstractEntity {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "item_name")
  private String name;

  @Column(name = "item_description")
  private String description;

  @Column(name = "item_rarity")
  private String rarity;
}
