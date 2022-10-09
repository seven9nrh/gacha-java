package com.seven9nrh.gachajava.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "gacha_player")
@Data
@EqualsAndHashCode(callSuper = true)
public class GachaPlayerEntity extends AbstractEntity {

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
