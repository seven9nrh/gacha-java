package com.seven9nrh.gachajava.domain.model;

public class GachaItemCategory {

  private final Identifier id;
  private final String name;
  private final String description;

  public GachaItemCategory(String name, String description) {
    this.id = new Identifier();
    this.name = name;
    this.description = description;
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
}
