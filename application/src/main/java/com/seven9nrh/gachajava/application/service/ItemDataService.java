package com.seven9nrh.gachajava.application.service;

import com.seven9nrh.gachajava.application.api.body.ItemDataBody;
import com.seven9nrh.gachajava.domain.model.ItemData;

public interface ItemDataService {
  ItemData newItemData(ItemDataBody form);

  ItemData getItemData(String id);

  ItemData modifyItemData(String id, ItemDataBody form);
}
