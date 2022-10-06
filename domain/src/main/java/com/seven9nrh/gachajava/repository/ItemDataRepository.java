package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.ItemData;
import java.util.Set;

public interface ItemDataRepository {
  Set<ItemData> getAllItemData();

  ItemData newItemData(ItemData itemData);

  ItemData getItemData(Identifier id);

  ItemData updateItemData(ItemData itemData);
}
