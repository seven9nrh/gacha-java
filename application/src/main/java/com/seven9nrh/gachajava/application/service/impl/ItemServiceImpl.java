package com.seven9nrh.gachajava.application.service.impl;

import com.seven9nrh.gachajava.application.api.v1.body.ItemBody;
import com.seven9nrh.gachajava.application.service.ItemService;
import com.seven9nrh.gachajava.domain.ItemManager;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Item;
import com.seven9nrh.gachajava.domain.model.Rarity;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

  private ItemManager itemManager;

  public ItemServiceImpl(ItemManager itemManager) {
    this.itemManager = itemManager;
  }

  @Override
  public Item newItem(ItemBody form) {
    Item item = new Item(
      form.getName(),
      form.getDescription(),
      Rarity.toRarity(form.getRarity())
    );

    return itemManager.newItem(item);
  }

  @Override
  public Item getItem(String id) {
    return itemManager.getItem(new Identifier(id));
  }

  @Override
  public Item modifyItem(String id, ItemBody form) {
    var item = itemManager.getItem(new Identifier(id));
    item =
      new Item(
        item.getId(),
        form.getName(),
        form.getDescription(),
        Rarity.toRarity(form.getRarity())
      );
    return itemManager.updateItem(item);
  }

  @Override
  public Set<Item> getItemList() {
    return itemManager.getAllItem();
  }
}
