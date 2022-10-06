package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.ItemDataDao;
import com.seven9nrh.gachajava.database.entity.ItemDataEntity;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.ItemDataRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDataRepositoryImpl implements ItemDataRepository {

  private ItemDataDao itemDataDao;

  public ItemDataRepositoryImpl(ItemDataDao itemDataDao) {
    this.itemDataDao = itemDataDao;
  }

  @Override
  public Set<ItemData> getAllItemData() {
    return itemDataDao
      .findAll()
      .stream()
      .map(
        entity ->
          new ItemData(
            new Identifier(entity.getId()),
            entity.getName(),
            entity.getDescription(),
            Rarity.toRarity(entity.getRarity())
          )
      )
      .collect(Collectors.toSet());
  }

  @Override
  public ItemData getItemData(Identifier id) {
    ItemDataEntity entity = itemDataDao.findById(id.getValue()).orElse(null);
    if (entity == null) {
      return null;
    }

    return toItemData(entity);
  }

  private ItemData toItemData(ItemDataEntity entity) {
    return new ItemData(
      new Identifier(entity.getId()),
      entity.getName(),
      entity.getDescription(),
      Rarity.toRarity(entity.getRarity())
    );
  }

  private ItemDataEntity toItemDataEntity(ItemData itemData) {
    var entity = new ItemDataEntity();
    entity.setId(itemData.getId().getValue());
    entity.setName(itemData.getName());
    entity.setDescription(itemData.getDescription());
    entity.setRarity(itemData.getRarity().getName());
    return entity;
  }

  @Override
  public ItemData newItemData(ItemData itemData) {
    var entity = toItemDataEntity(itemData);
    return toItemData(itemDataDao.save(entity));
  }

  @Override
  public ItemData updateItemData(ItemData itemData) {
    var entity = toItemDataEntity(itemData);
    return toItemData(itemDataDao.save(entity));
  }
}
