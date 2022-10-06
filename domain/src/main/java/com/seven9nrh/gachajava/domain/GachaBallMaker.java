package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.ItemData;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class GachaBallMaker {

  private final ItemDataManager itemManager;

  private Random random;

  public GachaBallMaker(ItemDataManager itemManager) {
    this.itemManager = itemManager;
    try {
      this.random = SecureRandom.getInstanceStrong();
    } catch (Exception e) {
      e.printStackTrace();
      this.random = new Random();
    }
  }

  public Set<GachaBall> makeBalls(int count) {
    // get item data from repository
    var itemDataSet = itemManager.getAllItemData();

    // when itemDataSet is empty, return empty set
    if (itemDataSet.isEmpty()) {
      return Collections.emptySet();
    }

    var itemDataList = new ArrayList<ItemData>();
    for (var itemData : itemDataSet) {
      var rarity = itemData.getRarity();
      var cnt = rarity.getProbability() * 1000;
      for (int i = 0; i < cnt; i++) {
        itemDataList.add(itemData);
      }
    }

    // shuffle itemdata list
    Collections.shuffle(itemDataList);

    Set<GachaBall> gachaBalls = new HashSet<>();
    for (int i = 0; i < count; i++) {
      var randomItemData = itemDataList.get(
        random.nextInt(itemDataList.size())
      );
      var gachaItem = new GachaItem(randomItemData);
      gachaBalls.add(new GachaBall(gachaItem));
    }
    return gachaBalls;
  }
}
