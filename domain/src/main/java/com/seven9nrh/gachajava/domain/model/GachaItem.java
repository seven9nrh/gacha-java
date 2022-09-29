package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;

public class GachaItem implements Serializable {

  private final Identifier id;
  private final String name;
  private final String description;
  private final Rarity rarity;
  private final GachaItemCategory category;

  public GachaItem(ItemData itemData, GachaItemCategory category) {
    this.id = new Identifier();
    this.name = itemData.getName();
    this.description = itemData.getDescription();
    this.rarity = itemData.getRarity();
    this.category = category;
  }

  public GachaItem(
    String name,
    String description,
    Rarity rarity,
    GachaItemCategory category
  ) {
    this.id = new Identifier();
    this.name = name;
    this.description = description;
    this.rarity = rarity;
    this.category = category;
  }

  public GachaItem(
    Identifier id,
    String name,
    String description,
    Rarity rarity,
    GachaItemCategory category
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.rarity = rarity;
    this.category = category;
  }

  public Identifier getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public GachaItemCategory getCategory() {
    return category;
  }
}
