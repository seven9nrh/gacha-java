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
    entity.setGachaItemId(gachaBall.getGachaItemId().getValue());
    return entity;
  }

  @Override
  public void deleteById(Identifier id) {
    gachaBallDao.deleteById(id.getValue());
  }

  @Override
  public GachaBall findById(Identifier id) {
    GachaBallEntity gachaBallEntity = gachaBallDao
      .findById(id.getValue())
      .orElse(null);
    if (gachaBallEntity == null) {
      return null;
    }
    return toGachaBall(gachaBallEntity);
  }

  private GachaBall toGachaBall(GachaBallEntity gachaBallEntity) {
    return new GachaBall(
      new Identifier(gachaBallEntity.getId()),
      new Identifier(gachaBallEntity.getGachaItemId()),
      new Identifier(gachaBallEntity.getGachaMachineId())
    );
  }
}
