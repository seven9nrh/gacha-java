package com.seven9nrh.gachajava.domain.model;

public enum Rarity {
  N("N", 0.8),
  R("R", 0.15),
  SR("SR", 0.04),
  SSR("SSR", 0.01);

  private final String name;
  private final double probability;

  Rarity(String name, double probability) {
    this.name = name;
    this.probability = probability;
  }

  public String getName() {
    return name;
  }

  public double getProbability() {
    return probability;
  }
}
