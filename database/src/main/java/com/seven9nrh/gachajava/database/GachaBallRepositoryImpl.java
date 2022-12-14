package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaBallDao;
import com.seven9nrh.gachajava.database.entity.GachaBallEntity;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
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
    entity.setGachaPlayerId(gachaBall.getGachaPlayerId().getValue());
    entity.setGachaItemId(gachaBall.getGachaItemId().getValue());
    entity.setIsEjected(false);
    entity.setIsOpened(false);
    return entity;
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
      new Identifier(gachaBallEntity.getGachaPlayerId())
    );
  }

  private ClosedGachaBall tClosedGachaBall(GachaBallEntity gachaBallEntity) {
    return new ClosedGachaBall(
      new Identifier(gachaBallEntity.getId()),
      new Identifier(gachaBallEntity.getGachaPlayerId())
    );
  }

  @Override
  public GachaBall openGachaBall(Identifier id) {
    GachaBallEntity gachaBallEntity = gachaBallDao.findByIdAndIsOpenedFalse(
      id.getValue()
    );
    if (gachaBallEntity == null) {
      return null;
    }
    gachaBallEntity.setIsOpened(true);
    return toGachaBall(gachaBallDao.save(gachaBallEntity));
  }

  @Override
  public ClosedGachaBall ejectGachaBall(Identifier id) {
    GachaBallEntity gachaBallEntity = gachaBallDao.findByIdAndIsEjectedFalse(
      id.getValue()
    );
    if (gachaBallEntity == null) {
      return null;
    }
    gachaBallEntity.setIsEjected(true);
    return tClosedGachaBall(gachaBallDao.save(gachaBallEntity));
  }
}
