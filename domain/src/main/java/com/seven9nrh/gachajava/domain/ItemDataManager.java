package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.repository.ItemDataRepository;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ItemDataManager {

  private ItemDataRepository itemDataRepository;

  public ItemDataManager(ItemDataRepository itemDataRepository) {
    this.itemDataRepository = itemDataRepository;
  }

  public Set<ItemData> getAllItemData() {
    return itemDataRepository.getAllItemData();
  }

  public ItemData getItemData(Identifier id) {
    return itemDataRepository.getItemData(id);
  }

  public ItemData newItemData(ItemData itemData) {
    return itemDataRepository.newItemData(itemData);
  }

  public ItemData updateItemData(ItemData itemData) {
    return itemDataRepository.updateItemData(itemData);
  }
}
