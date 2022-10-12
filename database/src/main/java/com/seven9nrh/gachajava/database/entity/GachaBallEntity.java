package com.seven9nrh.gachajava.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "gacha_ball")
@Data
@EqualsAndHashCode(callSuper = true)
@Where(clause = "is_deleted = false")
public class GachaBallEntity extends AbstractEntity {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "gacha_player_id")
  private String gachaPlayerId;

  @Column(name = "gacha_item_id")
  private String gachaItemId;

  @Column(name = "is_opened", nullable = false)
  private Boolean isOpened;

  @Column(name = "is_ejected", nullable = false)
  private Boolean isEjected;
}
