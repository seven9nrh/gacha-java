package com.seven9nrh.gachajava.application.service;

import com.seven9nrh.gachajava.application.api.body.ItemDataBody;
import com.seven9nrh.gachajava.domain.ItemDataManager;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.domain.model.Rarity;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ItemDataServiceImpl implements ItemDataService {

  private ItemDataManager itemDataManager;

  public ItemDataServiceImpl(ItemDataManager itemDataManager) {
    this.itemDataManager = itemDataManager;
  }

  @Override
  public ItemData newItemData(ItemDataBody form) {
    ItemData itemData = new ItemData(
      form.getName(),
      form.getDescription(),
      Rarity.toRarity(form.getRarity())
    );

    return itemDataManager.newItemData(itemData);
  }

  @Override
  public ItemData getItemData(String id) {
    return itemDataManager.getItemData(new Identifier(id));
  }

  @Override
  public ItemData modifyItemData(String id, ItemDataBody form) {
    var itemData = itemDataManager.getItemData(new Identifier(id));
    itemData =
      new ItemData(
        itemData.getId(),
        form.getName(),
        form.getDescription(),
        Rarity.toRarity(form.getRarity())
      );
    return itemDataManager.updateItemData(itemData);
  }

  @Override
  public Set<ItemData> getItemDataList() {
    return itemDataManager.getAllItemData();
  }
}
