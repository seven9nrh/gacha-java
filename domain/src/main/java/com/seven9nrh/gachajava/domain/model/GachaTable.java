package com.seven9nrh.gachajava.domain.model;

public class GachaTable {

  private Rarity rarity;
  private int weight;

  public GachaTable(Rarity rarity, int weight) {
    this.rarity = rarity;
    this.weight = weight;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public int getWeight() {
    return weight;
  }
}
