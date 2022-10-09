package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Item;
import java.util.Set;

public interface ItemRepository {
  Set<Item> getAllItem();

  Item newItem(Item item);

  Item getItem(Identifier id);

  Item updateItem(Item item);
}
