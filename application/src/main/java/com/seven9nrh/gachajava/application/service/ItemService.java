package com.seven9nrh.gachajava.application.service;

import com.seven9nrh.gachajava.application.api.v1.body.ItemBody;
import com.seven9nrh.gachajava.domain.model.Item;
import java.util.Set;

public interface ItemService {
  Item newItem(ItemBody form);

  Item getItem(String id);

  Item modifyItem(String id, ItemBody form);

  Set<Item> getItemList();
}
