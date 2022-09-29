package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.GachaTable;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.repository.GachaTableRepository;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GachaBallMaker {

  private final ItemManager itemManager;

  private final GachaTableRepository gachaTableRepository;

  private Random random;

  public GachaBallMaker(
    ItemManager itemManager,
    GachaTableRepository gachaTableRepository
  ) {
    this.itemManager = itemManager;
    this.gachaTableRepository = gachaTableRepository;
    try {
      this.random = SecureRandom.getInstanceStrong();
    } catch (Exception e) {
      e.printStackTrace();
      this.random = new Random();
    }
  }

  public Set<GachaBall> makeBalls(GachaItemCategory category, int count) {
    // get item data from repository
    var itemDataSet = itemManager.getItemData(category);
    // get gacha table from repository
    var gachaTableSet = gachaTableRepository.getGachaTable(category);

    // total weight
    var totalWeight = gachaTableSet
      .stream()
      .mapToLong(GachaTable::getWeight)
      .sum();

    // persentage
    double percentage = 100.0 / totalWeight;

    var itemDataList = new ArrayList<ItemData>();
    for (var itemData : itemDataSet) {
      var rarity = itemData.getRarity();
      var weight = gachaTableSet
        .stream()
        .filter(g -> g.getRarity() == rarity)
        .mapToLong(GachaTable::getWeight)
        .sum();
      var maxConunt = (int) (weight * percentage);
      for (int i = 0; i < maxConunt; i++) {
        itemDataList.add(itemData);
      }
    }

    // shuffle itemdata list
    Collections.shuffle(itemDataList);

    // randum itemdata
    var randomItemData = itemDataList.get(random.nextInt(itemDataList.size()));

    Set<GachaBall> gachaBalls = new HashSet<>();
    for (int i = 0; i < count; i++) {
      var gachaItem = new GachaItem(randomItemData, category);
      gachaBalls.add(new GachaBall(gachaItem));
    }
    return gachaBalls;
  }
}
