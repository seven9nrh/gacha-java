package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.repository.GachaPlayerRepository;
import org.springframework.stereotype.Component;

@Component
public class GachaPlayerManager {

  private final GachaPlayerRepository gachaPlayerRepository;

  private final GachaBallMaker gachaBallMaker;

  public GachaPlayerManager(
    GachaPlayerRepository gachaPlayerRepository,
    GachaBallMaker gachaBallMaker
  ) {
    this.gachaPlayerRepository = gachaPlayerRepository;
    this.gachaBallMaker = gachaBallMaker;
  }

  public void saveGachaPlayer(GachaPlayer gachaPlayer) {
    gachaPlayerRepository.save(gachaPlayer);
  }

  public void deleteGachaPlayer(Identifier id) {
    gachaPlayerRepository.delete(id);
  }

  public GachaPlayer buyGachaBalls(Identifier id, int qty) {
    GachaPlayer gachaPlayer = gachaPlayerRepository.findById(id);

    gachaPlayer.addGachaBall(gachaBallMaker.makeBalls(qty, gachaPlayer));

    saveGachaPlayer(gachaPlayer);

    return gachaPlayer;
  }

  public GachaPlayer newGachaPlayer(
    String name,
    String description,
    int wallet
  ) {
    GachaPlayer gachaPlayer = new GachaPlayer(name, description, wallet);

    saveGachaPlayer(gachaPlayer);

    return gachaPlayer;
  }

  public GachaPlayer findById(Identifier id) {
    return gachaPlayerRepository.findById(id);
  }

  public ClosedGachaBall pullGachaBall(Identifier id) {
    GachaPlayer gachaPlayer = gachaPlayerRepository.findById(id);
    ClosedGachaBall gachaBall = gachaBallMaker.pullGachaBall(gachaPlayer);
    return gachaBall;
  }

  public GachaBall openGachaBall(Identifier id) {
    return gachaBallMaker.getGachaBall(id);
  }

  public GachaItem getGachaItem(Identifier id) {
    return gachaBallMaker.getGachaItem(id);
  }
}
