package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gacha_ball")
public class GachaBallEntity implements Serializable {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "gacha_machine_id")
  private String gachaMachineId;

  @Column(name = "is_openned")
  private Boolean isOpenned;

  @Column(name = "gacha_item_id")
  private String gachaItemId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean getIsOpenned() {
    return isOpenned;
  }

  public void setIsOpenned(Boolean isOpenned) {
    this.isOpenned = isOpenned;
  }

  public String getGachaItemId() {
    return gachaItemId;
  }

  public void setGachaItemId(String gachaItemId) {
    this.gachaItemId = gachaItemId;
  }
}
