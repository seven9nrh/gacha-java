package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class ItemEntity implements Serializable {

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
