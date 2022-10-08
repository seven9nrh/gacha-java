package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import lombok.Value;

@Value
public class GachaItem implements Serializable {

  private Identifier id;

  private String name;
  private String description;
  private Rarity rarity;

  public GachaItem(Item item) {
    this.id = Identifier.generate();
    this.name = item.getName();
    this.description = item.getDescription();
    this.rarity = item.getRarity();
  }

  public GachaItem(String name, String description, Rarity rarity) {
    this.id = Identifier.generate();
    this.name = name;
    this.description = description;
    this.rarity = rarity;
  }

  public GachaItem(
    Identifier id,
    String name,
    String description,
    Rarity rarity
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.rarity = rarity;
  }
}
