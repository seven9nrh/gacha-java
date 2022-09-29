package com.seven9nrh.gachajava.domain.model;

public class GachaBall {

  private final GachaItem item;

  public GachaBall(GachaItem item) {
    this.item = item;
  }

  public Identifier getIdentifier() {
    return item.getId();
  }

  public GachaItem getItem() {
    return item;
  }
}
