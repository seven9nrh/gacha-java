package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;

public class ItemData implements Serializable {

  private final String name;
  private final String description;
  private final Rarity rarity;

  public ItemData(String name, String description, Rarity rarity) {
    this.name = name;
    this.description = description;
    this.rarity = rarity;
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
}
