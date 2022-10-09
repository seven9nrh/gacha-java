package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Item;
import com.seven9nrh.gachajava.repository.ItemRepository;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ItemManager {

  private ItemRepository itemRepository;

  public ItemManager(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Set<Item> getAllItem() {
    return itemRepository.getAllItem();
  }

  public Item getItem(Identifier id) {
    return itemRepository.getItem(id);
  }

  public Item newItem(Item item) {
    return itemRepository.newItem(item);
  }

  public Item updateItem(Item item) {
    return itemRepository.updateItem(item);
  }
}
