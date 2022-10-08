package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaBallDao;
import com.seven9nrh.gachajava.database.entity.GachaBallEntity;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.repository.GachaBallRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GachaBallRepositoryImpl implements GachaBallRepository {

  private final GachaBallDao gachaBallDao;

  public GachaBallRepositoryImpl(GachaBallDao gachaBallDao) {
    this.gachaBallDao = gachaBallDao;
  }

  @Override
  public void save(GachaBall gachaBall) {
    gachaBallDao.save(toGachaBallEntity(gachaBall));
  }

  private GachaBallEntity toGachaBallEntity(GachaBall gachaBall) {
    var entity = new GachaBallEntity();
    entity.setId(gachaBall.getId().getValue());
    entity.setGachaMachineId(gachaBall.getGachaMachineId().getValue());
    entity.setIsOpenned(gachaBall.isOpenned());
    entity.setGachaItemId(gachaBall.getItem().getId().getValue());
    return entity;
  }

  @Override
  public void deleteById(Identifier id) {
    gachaBallDao.deleteById(id.getValue());
  }
}
