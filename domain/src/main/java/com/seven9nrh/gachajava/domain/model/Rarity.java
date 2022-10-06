package com.seven9nrh.gachajava.domain.model;

import lombok.Getter;

@Getter
public enum Rarity {
  N("N", 0.8),
  R("R", 0.15),
  SR("SR", 0.04),
  SSR("SSR", 0.01);

  private String name;
  private double probability;

  Rarity(String name, double probability) {
    this.name = name;
    this.probability = probability;
  }

  public static Rarity toRarity(String name) {
    for (Rarity rarity : Rarity.values()) {
      if (rarity.name.equals(name)) {
        return rarity;
      }
    }
    throw new IllegalArgumentException("Invalid rarity name: " + name);
  }
}
