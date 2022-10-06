package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gacha_machine")
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getMaxStock() {
    return maxStock;
  }

  public void setMaxStock(Integer maxStock) {
    this.maxStock = maxStock;
  }
}
