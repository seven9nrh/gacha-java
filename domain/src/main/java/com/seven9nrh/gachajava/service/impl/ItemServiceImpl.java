package com.seven9nrh.gachajava.service.impl;

import com.seven9nrh.gachajava.domain.ItemManager;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Item;
import com.seven9nrh.gachajava.service.ItemService;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

  private ItemManager itemManager;

  public ItemServiceImpl(ItemManager itemManager) {
    this.itemManager = itemManager;
  }

  @Override
  public Item newItem(Item item) {
    // Item item = new Item(
    //   form.getName(),
    //   form.getDescription(),
    //   Rarity.toRarity(form.getRarity())
    // );

    return itemManager.newItem(item);
  }

  @Override
  public Item getItem(String id) {
    return itemManager.getItem(new Identifier(id));
  }

  @Override
  public Item modifyItem(Item item) {
    return itemManager.updateItem(item);
  }

  @Override
  public Set<Item> getItemList() {
    return itemManager.getAllItem();
  }
}
