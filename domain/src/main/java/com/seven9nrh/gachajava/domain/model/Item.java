package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Item implements Serializable {

  private Identifier id;

  private String name;
  private String description;
  private Rarity rarity;

  public Item(String name, String description, Rarity rarity) {
    this.id = Identifier.generate();
    this.name = name;
    this.description = description;
    this.rarity = rarity;
  }
}
