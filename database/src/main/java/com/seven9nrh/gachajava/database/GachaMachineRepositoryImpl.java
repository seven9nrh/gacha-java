package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaBallDao;
import com.seven9nrh.gachajava.database.dao.GachaMachineDao;
import com.seven9nrh.gachajava.database.entity.GachaBallEntity;
import com.seven9nrh.gachajava.database.entity.GachaMachineEntity;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.repository.GachaMachineRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

@Repository
public class GachaMachineRepositoryImpl implements GachaMachineRepository {

  private final GachaMachineDao gachaMachineDao;

  private final GachaBallDao gachaBallDao;

  public GachaMachineRepositoryImpl(
    GachaMachineDao gachaMachineDao,
    GachaBallDao gachaBallDao
  ) {
    this.gachaMachineDao = gachaMachineDao;
    this.gachaBallDao = gachaBallDao;
  }

  @Override
  public void delete(Identifier id) {
    gachaMachineDao.deleteById(id.getValue());
  }

  @Override
  public GachaMachine getGachaMachine(Identifier id) {
    GachaMachineEntity entity = gachaMachineDao
      .findById(id.getValue())
      .orElse(null);
    if (entity == null) {
      return null;
    }

    GachaMachine gachaMachine = new GachaMachine(
      new Identifier(entity.getId()),
      entity.getName(),
      entity.getDescription(),
      entity.getPrice(),
      entity.getMaxStock()
    );

    var condition = new GachaBallEntity();
    condition.setGachaMachineId(entity.getId());
    var gachaBallEntities = gachaBallDao.findAll(Example.of(condition));
    Set<ClosedGachaBall> gachaBalls = gachaBallEntities
      .stream()
      .map(
        gachaBallEntity -> {
          return new ClosedGachaBall(
            new Identifier(gachaBallEntity.getId()),
            gachaMachine.getId()
          );
        }
      )
      .collect(Collectors.toSet());

    gachaMachine.addGachaBall(gachaBalls);
    return gachaMachine;
  }

  @Override
  public void save(GachaMachine gachaMachine) {
    GachaMachineEntity entity = new GachaMachineEntity();
    entity.setId(gachaMachine.getId().getValue());
    entity.setName(gachaMachine.getName());
    entity.setDescription(gachaMachine.getDescription());
    entity.setPrice(gachaMachine.getPrice());
    entity.setMaxStock(gachaMachine.getMaxStock());
    gachaMachineDao.save(entity);
  }
}
