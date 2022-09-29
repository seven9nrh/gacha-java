package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.repository.GachaMachineRepository;

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
  public void deleteGachaMachine(String id) {
    repository.delete(id);
  }

  // refill Gatya balls.
  public void refillGachaBalls(String id, int qty, GachaItemCategory category) {
    // get gacha machine.
    GachaMachine gachaMachine = getGachaMachine(id);

    // get current stock.
    int currentStock = gachaMachine.getCurrentStock();

    // get max stock.
    int maxStock = gachaMachine.getMaxStock();

    // check if current stock + amount is less than max stock.
    if (currentStock + qty > maxStock) {
      // throw exception.
      throw new IllegalArgumentException("Stock is full.");
    }

    // create gacha balls.
    for (int i = 0; i < qty; i++) {
      // add gacha ball to gacha machine.
      gachaMachine.addGachaBall(gachaBallMaker.makeBalls(category, qty));
    }

    // set current stock.
    gachaMachine.setCurrentStock(currentStock + qty);

    // save gacha machine.
    saveGachaMachine(gachaMachine);
  }

  private GachaMachine getGachaMachine(String id) {
    return repository.findById(id);
  }
}
