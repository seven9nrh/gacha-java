package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.repository.GachaBallRepository;
import com.seven9nrh.gachajava.repository.GachaItemRepository;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class GachaBallMaker {

  private final ItemDataManager itemManager;

  private final GachaItemRepository gachaItemRepository;

  private final GachaBallRepository gachaBallRepository;

  private Random random;

  public GachaBallMaker(
    ItemDataManager itemManager,
    GachaItemRepository gachaItemRepository,
    GachaBallRepository gachaBallRepository
  ) {
    this.itemManager = itemManager;
    this.gachaItemRepository = gachaItemRepository;
    this.gachaBallRepository = gachaBallRepository;
    try {
      this.random = SecureRandom.getInstanceStrong();
    } catch (Exception e) {
      e.printStackTrace();
      this.random = new Random();
    }
  }

  public Set<GachaBall> makeBalls(int count, GachaMachine gachaMachine) {
    // get item data from repository
    var itemDataSet = itemManager.getAllItemData();

    // when itemDataSet is empty, return empty set
    if (itemDataSet.isEmpty()) {
      return Collections.emptySet();
    }

    var itemDataList = new ArrayList<ItemData>();

    // count of rarity from itemDataSet
    var rarityCountMap = itemDataSet
      .stream()
      .map(i -> i.getRarity())
      .collect(
        Collectors.groupingBy(r -> r, LinkedHashMap::new, Collectors.counting())
      );

    // make itemDataList
    itemDataSet.forEach(
      itemData -> {
        var rarity = itemData.getRarity();
        var cnt = (rarity.getProbability() / rarityCountMap.get(rarity)) * 1000;
        for (int i = 0; i < cnt; i++) {
          itemDataList.add(itemData);
        }
      }
    );

    // shuffle itemdata list
    Collections.shuffle(itemDataList);

    Set<GachaBall> gachaBalls = new HashSet<>();
    for (int i = 0; i < count; i++) {
      var randomItemData = itemDataList.get(
        random.nextInt(itemDataList.size())
      );
      var gachaItem = new GachaItem(randomItemData);
      gachaItemRepository.save(gachaItem);

      GachaBall gachaBall = new GachaBall(gachaItem, gachaMachine.getId());
      gachaBallRepository.save(gachaBall);

      gachaBalls.add(gachaBall);
    }
    return gachaBalls;
  }

  public GachaBall pullGachaBall(GachaMachine gachaMachine) {
    Set<GachaBall> gachaBalls = gachaMachine.getGachaBalls();
    if (gachaBalls.isEmpty()) {
      return null;
    }
    var gachaBall = gachaBalls.iterator().next();
    gachaBalls.remove(gachaBall);
    return gachaBall;
  }
}
