package com.seven9nrh.gachajava.service;

import com.seven9nrh.gachajava.domain.model.Item;
import java.util.Set;

public interface ItemService {
  Item newItem(Item item);

  Item getItem(String id);

  Item modifyItem(Item item);

  Set<Item> getItemList();
}
