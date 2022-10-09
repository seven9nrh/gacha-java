package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.ItemDao;
import com.seven9nrh.gachajava.database.entity.ItemEntity;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Item;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.ItemRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

  private ItemDao itemDao;

  public ItemRepositoryImpl(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public Set<Item> getAllItem() {
    return itemDao
      .findAll()
      .stream()
      .map(
        entity ->
          new Item(
            new Identifier(entity.getId()),
            entity.getName(),
            entity.getDescription(),
            Rarity.toRarity(entity.getRarity())
          )
      )
      .collect(Collectors.toSet());
  }

  @Override
  public Item getItem(Identifier id) {
    ItemEntity entity = itemDao.findById(id.getValue()).orElse(null);
    if (entity == null) {
      return null;
    }

    return toItem(entity);
  }

  private Item toItem(ItemEntity entity) {
    return new Item(
      new Identifier(entity.getId()),
      entity.getName(),
      entity.getDescription(),
      Rarity.toRarity(entity.getRarity())
    );
  }

  private ItemEntity toItemEntity(Item item) {
    var entity = new ItemEntity();
    entity.setId(item.getId().getValue());
    entity.setName(item.getName());
    entity.setDescription(item.getDescription());
    entity.setRarity(item.getRarity().getName());
    return entity;
  }

  @Override
  public Item newItem(Item item) {
    var entity = toItemEntity(item);
    return toItem(itemDao.save(entity));
  }

  @Override
  public Item updateItem(Item item) {
    Optional<ItemEntity> findById = itemDao.findById(item.getId().getValue());
    if (findById.isEmpty()) {
      throw new IdentifierNotFoundException(item.getId());
    }
    // update item
    var entity = findById.get();
    entity.setName(item.getName());
    entity.setDescription(item.getDescription());
    entity.setRarity(item.getRarity().getName());
    return toItem(itemDao.save(entity));
  }
}
