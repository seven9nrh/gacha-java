package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.repository.ItemDataRepository;
import java.util.Set;

public class ItemManager {

  private ItemDataRepository itemDataRepository;

  public ItemManager(ItemDataRepository itemDataRepository) {
    this.itemDataRepository = itemDataRepository;
  }

  public Set<ItemData> getItemData(GachaItemCategory category) {
    return itemDataRepository.getItemData(category);
  }
}
