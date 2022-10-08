package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Item;
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

  private final ItemManager itemManager;

  private final GachaItemRepository gachaItemRepository;

  private final GachaBallRepository gachaBallRepository;

  private Random random;

  public GachaBallMaker(
    ItemManager itemManager,
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

  public Set<ClosedGachaBall> makeBalls(int count, GachaPlayer gachaPlayer) {
    var itemSet = itemManager.getAllItem();

    if (itemSet.isEmpty()) {
      return Collections.emptySet();
    }

    var itemList = new ArrayList<Item>();

    var rarityCountMap = itemSet
      .stream()
      .map(Item::getRarity)
      .collect(
        Collectors.groupingBy(r -> r, LinkedHashMap::new, Collectors.counting())
      );

    itemSet.forEach(
      item -> {
        var rarity = item.getRarity();
        var cnt = (rarity.getProbability() / rarityCountMap.get(rarity)) * 1000;
        for (int i = 0; i < cnt; i++) {
          itemList.add(item);
        }
      }
    );

    // shuffle item list
    Collections.shuffle(itemList);

    Set<ClosedGachaBall> gachaBalls = new HashSet<>();
    for (int i = 0; i < count; i++) {
      var randomItem = itemList.get(random.nextInt(itemList.size()));
      var gachaItem = new GachaItem(randomItem);
      gachaItemRepository.save(gachaItem);

      GachaBall gachaBall = new GachaBall(
        gachaItem.getId(),
        gachaPlayer.getId()
      );
      gachaBallRepository.save(gachaBall);

      gachaBalls.add(toClosed(gachaBall));
    }
    return gachaBalls;
  }

  private ClosedGachaBall toClosed(GachaBall gachaBall) {
    return new ClosedGachaBall(gachaBall.getId(), gachaBall.getGachaPlayerId());
  }

  public ClosedGachaBall pullGachaBall(GachaPlayer gachaPlayer) {
    Set<ClosedGachaBall> gachaBalls = gachaPlayer.getGachaBalls();
    if (gachaBalls.isEmpty()) {
      return null;
    }
    var gachaBall = gachaBalls.iterator().next();
    gachaBalls.remove(gachaBall);
    return gachaBall;
  }

  public GachaBall getGachaBall(Identifier id) {
    return gachaBallRepository.findById(id);
  }

  public GachaItem getGachaItem(Identifier id) {
    return gachaItemRepository.findById(id);
  }
}
