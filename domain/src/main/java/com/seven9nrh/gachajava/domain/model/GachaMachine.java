package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Value;

@Value
public class GachaMachine implements Serializable {

  private Identifier id;
  private String name;
  private String description;
  private int price;
  private int maxStock;
  private Set<GachaBall> gachaBalls;

  public GachaMachine(
    Identifier id,
    String name,
    String description,
    int price,
    int maxStock
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.maxStock = maxStock;
    this.gachaBalls = new HashSet<>();
  }

  public GachaMachine(
    String name,
    String description,
    int price,
    int maxStock
  ) {
    this.id = Identifier.generate();
    this.name = name;
    this.description = description;
    this.price = price;
    this.maxStock = maxStock;
    this.gachaBalls = Set.of();
  }

  public void addGachaBall(Set<GachaBall> makeBalls) {
    this.gachaBalls.addAll(makeBalls);
  }
}
