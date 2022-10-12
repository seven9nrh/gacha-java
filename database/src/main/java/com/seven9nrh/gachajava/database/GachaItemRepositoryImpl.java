package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaItemDao;
import com.seven9nrh.gachajava.database.entity.GachaItemEntity;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.GachaItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GachaItemRepositoryImpl implements GachaItemRepository {

  private GachaItemDao gachaItemDao;

  public GachaItemRepositoryImpl(GachaItemDao gachaItemDao) {
    this.gachaItemDao = gachaItemDao;
  }

  @Override
  public void save(GachaItem gachaItem) {
    gachaItemDao.save(toGachaItemEntity(gachaItem));
  }

  private GachaItemEntity toGachaItemEntity(GachaItem gachaItem) {
    var entity = new GachaItemEntity();
    entity.setId(gachaItem.getId().getValue());
    entity.setName(gachaItem.getName());
    entity.setDescription(gachaItem.getDescription());
    entity.setRarity(gachaItem.getRarity().getName());
    entity.setGachaPlayerId(gachaItem.getGachaPlayerId().getValue());
    return entity;
  }

  @Override
  public GachaItem findById(Identifier id) {
    return gachaItemDao
      .findById(id.getValue())
      .map(this::toGachaItem)
      .orElse(null);
  }

  private GachaItem toGachaItem(GachaItemEntity gachaItemEntity) {
    return new GachaItem(
      new Identifier(gachaItemEntity.getId()),
      gachaItemEntity.getName(),
      gachaItemEntity.getDescription(),
      Rarity.toRarity(gachaItemEntity.getRarity()),
      new Identifier(gachaItemEntity.getGachaPlayerId())
    );
  }
}
