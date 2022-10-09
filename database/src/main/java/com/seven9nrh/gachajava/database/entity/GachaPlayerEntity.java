package com.seven9nrh.gachajava.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gacha_player")
@Data
public class GachaPlayerEntity implements Serializable {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "gacha_player_name")
  private String name;

  @Column(name = "gacha_player_description")
  private String description;

  @Column(name = "gacha_player_wallet")
  private Integer wallet;
}
