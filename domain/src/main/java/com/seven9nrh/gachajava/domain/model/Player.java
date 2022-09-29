package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

  private final Identifier id;
  private final String name;
  private final String description;
  private int money;
  private final List<GachaBall> gachaBalls;
  private final List<GachaItem> gachaItems;

  public Player(String name, String description, int money) {
    this.id = new Identifier();
    this.name = name;
    this.description = description;
    this.money = money;
    this.gachaBalls = new ArrayList<>();
    this.gachaItems = new ArrayList<>();
  }

  public Player(Identifier id, String name, String description, int money) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.money = money;
    this.gachaBalls = new ArrayList<>();
    this.gachaItems = new ArrayList<>();
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

  public int getMoney() {
    return money;
  }

  public List<GachaBall> getGachaBalls() {
    return gachaBalls;
  }

  public List<GachaItem> getGachaItems() {
    return gachaItems;
  }

  public void addGachaBall(GachaBall gachaBall) {
    gachaBalls.add(gachaBall);
  }
}
