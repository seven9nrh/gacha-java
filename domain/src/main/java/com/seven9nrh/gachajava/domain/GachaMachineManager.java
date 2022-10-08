package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.repository.GachaMachineRepository;
import org.springframework.stereotype.Component;

@Component
public class GachaMachineManager {

  private final GachaMachineRepository repository;

  private final GachaBallMaker gachaBallMaker;

  public GachaMachineManager(
    GachaMachineRepository repository,
    GachaBallMaker gachaBallMaker
  ) {
    this.repository = repository;
    this.gachaBallMaker = gachaBallMaker;
  }

  // Save a gacha machine.
  public void saveGachaMachine(GachaMachine gachaMachine) {
    repository.save(gachaMachine);
  }

  // Delete a gacha machine.
  public void deleteGachaMachine(Identifier id) {
    repository.delete(id);
  }

  public GachaMachine refillGachaBalls(Identifier id, int qty) {
    // get gacha machine.
    GachaMachine gachaMachine = repository.getGachaMachine(id);

    // add gacha ball to gacha machine.
    gachaMachine.addGachaBall(gachaBallMaker.makeBalls(qty, gachaMachine));

    // save gacha machine.
    saveGachaMachine(gachaMachine);

    return gachaMachine;
  }

  public GachaMachine newGachaMachine(
    String name,
    String description,
    int price,
    int maxStock
  ) {
    // create gacha machine.
    GachaMachine gachaMachine = new GachaMachine(
      name,
      description,
      price,
      maxStock
    );

    // save gacha machine.
    saveGachaMachine(gachaMachine);

    // return gacha machine.
    return gachaMachine;
  }

  public GachaMachine getGachaMachine(Identifier id) {
    return repository.getGachaMachine(id);
  }

  public ClosedGachaBall pullGachaBall(Identifier id) {
    GachaMachine gachaMachine = repository.getGachaMachine(id);
    ClosedGachaBall gachaBall = gachaBallMaker.pullGachaBall(gachaMachine);
    return gachaBall;
  }

  public GachaBall openGachaBall(Identifier id) {
    return gachaBallMaker.getGachaBall(id);
  }
}
