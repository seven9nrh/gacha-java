package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GachaMachine implements Serializable {

  private final Identifier id;
  private final String name;
  private final String description;
  private final int price;
  private final int maxStock;
  private int currentStock;
  private final Set<GachaBall> gachaBalls;

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

  public Identifier getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getPrice() {
    return price;
  }

  public int getMaxStock() {
    return maxStock;
  }

  public int getCurrentStock() {
    return currentStock;
  }

  public void setCurrentStock(int currentStock) {
    this.currentStock = currentStock;
  }

  public Set<GachaBall> getGachaBalls() {
    return gachaBalls;
  }

  public void addGachaBall(Set<GachaBall> makeBalls) {
    this.gachaBalls.addAll(makeBalls);
  }
}
