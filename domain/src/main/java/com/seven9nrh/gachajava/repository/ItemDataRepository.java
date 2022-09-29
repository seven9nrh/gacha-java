package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.ItemData;
import java.util.Set;

public interface ItemDataRepository {
  public Set<ItemData> getItemData(GachaItemCategory category);
}
