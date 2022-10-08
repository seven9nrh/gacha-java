package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gacha_machine")
@Data
public class GachaMachineEntity implements Serializable {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "gacha_machine_name")
  private String name;

  @Column(name = "gacha_machine_description")
  private String description;

  @Column(name = "gacha_machine_price")
  private Integer price;

  @Column(name = "gacha_machine_max_stock")
  private Integer maxStock;
}
