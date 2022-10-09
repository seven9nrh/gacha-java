package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaBallDao;
import com.seven9nrh.gachajava.database.dao.GachaPlayerDao;
import com.seven9nrh.gachajava.database.entity.GachaBallEntity;
import com.seven9nrh.gachajava.database.entity.GachaPlayerEntity;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.repository.GachaPlayerRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

@Repository
public class GachaPlayerRepositoryImpl implements GachaPlayerRepository {

  private final GachaPlayerDao gachaPlayerDao;

  private final GachaBallDao gachaBallDao;

  public GachaPlayerRepositoryImpl(
    GachaPlayerDao gachaPlayerDao,
    GachaBallDao gachaBallDao
  ) {
    this.gachaPlayerDao = gachaPlayerDao;
    this.gachaBallDao = gachaBallDao;
  }

  @Override
  public void delete(Identifier id) {
    gachaPlayerDao.deleteById(id.getValue());
  }

  @Override
  public GachaPlayer findById(Identifier id) {
    GachaPlayerEntity entity = gachaPlayerDao
      .findById(id.getValue())
      .orElse(null);
    if (entity == null) {
      return null;
    }

    GachaPlayer gachaPlayer = new GachaPlayer(
      new Identifier(entity.getId()),
      entity.getName(),
      entity.getDescription(),
      entity.getWallet()
    );

    var condition = new GachaBallEntity();
    condition.setGachaPlayerId(entity.getId());
    var gachaBallEntities = gachaBallDao.findAll(Example.of(condition));
    Set<ClosedGachaBall> gachaBalls = gachaBallEntities
      .stream()
      .map(
        gachaBallEntity -> {
          return new ClosedGachaBall(
            new Identifier(gachaBallEntity.getId()),
            gachaPlayer.getId()
          );
        }
      )
      .collect(Collectors.toSet());

    gachaPlayer.addGachaBall(gachaBalls);
    return gachaPlayer;
  }

  @Override
  public void save(GachaPlayer gachaPlayer) {
    GachaPlayerEntity entity = new GachaPlayerEntity();
    entity.setId(gachaPlayer.getId().getValue());
    entity.setName(gachaPlayer.getName());
    entity.setDescription(gachaPlayer.getDescription());
    entity.setWallet(gachaPlayer.getWallet());
    gachaPlayerDao.save(entity);
  }
}
