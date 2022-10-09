package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Value;

@Value
public class GachaPlayer implements Serializable {

  private Identifier id;
  private String name;
  private String description;
  private int wallet;
  private Set<ClosedGachaBall> gachaBalls;
  private Set<GachaItem> gachaItems;

  public GachaPlayer(
    Identifier id,
    String name,
    String description,
    int wallet
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.wallet = wallet;
    this.gachaBalls = new HashSet<>();
    this.gachaItems = new HashSet<>();
  }

  public GachaPlayer(String name, String description, int wallet) {
    this.id = Identifier.generate();
    this.name = name;
    this.description = description;
    this.wallet = wallet;
    this.gachaBalls = Set.of();
    this.gachaItems = Set.of();
  }

  public void addGachaBall(Set<ClosedGachaBall> makeBalls) {
    this.gachaBalls.addAll(makeBalls);
  }

  public void addGachaItem(Set<GachaItem> gachaItems) {
    this.gachaItems.addAll(gachaItems);
  }
}
