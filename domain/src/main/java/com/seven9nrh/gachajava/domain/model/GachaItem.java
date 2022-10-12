package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import lombok.Value;

@Value
public class GachaItem implements Serializable {

  private Identifier id;

  private String name;
  private String description;
  private Rarity rarity;
  private Identifier gachaPlayerId;

  public GachaItem(Item item, Identifier gachaPlayerId) {
    this.id = Identifier.generate();
    this.name = item.getName();
    this.description = item.getDescription();
    this.rarity = item.getRarity();
    this.gachaPlayerId = gachaPlayerId;
  }

  public GachaItem(
    String name,
    String description,
    Rarity rarity,
    Identifier gachaPlayerId
  ) {
    this.id = Identifier.generate();
    this.name = name;
    this.description = description;
    this.rarity = rarity;
    this.gachaPlayerId = gachaPlayerId;
  }

  public GachaItem(
    Identifier id,
    String name,
    String description,
    Rarity rarity,
    Identifier gachaPlayerId
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.rarity = rarity;
    this.gachaPlayerId = gachaPlayerId;
  }
}
