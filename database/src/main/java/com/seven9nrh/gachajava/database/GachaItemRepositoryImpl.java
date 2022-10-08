package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaItemDao;
import com.seven9nrh.gachajava.database.entity.GachaItemEntity;
import com.seven9nrh.gachajava.domain.model.GachaItem;
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
    return entity;
  }
}
