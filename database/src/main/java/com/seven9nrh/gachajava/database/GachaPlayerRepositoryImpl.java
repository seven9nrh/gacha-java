package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.database.dao.GachaBallDao;
import com.seven9nrh.gachajava.database.dao.GachaItemDao;
import com.seven9nrh.gachajava.database.dao.GachaPlayerDao;
import com.seven9nrh.gachajava.database.entity.GachaBallEntity;
import com.seven9nrh.gachajava.database.entity.GachaItemEntity;
import com.seven9nrh.gachajava.database.entity.GachaPlayerEntity;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.GachaPlayerRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

@Repository
public class GachaPlayerRepositoryImpl implements GachaPlayerRepository {

  private final GachaPlayerDao gachaPlayerDao;

  private final GachaBallDao gachaBallDao;

  private final GachaItemDao gachaItemDao;

  public GachaPlayerRepositoryImpl(
    GachaPlayerDao gachaPlayerDao,
    GachaBallDao gachaBallDao,
    GachaItemDao gachaItemDao
  ) {
    this.gachaPlayerDao = gachaPlayerDao;
    this.gachaBallDao = gachaBallDao;
    this.gachaItemDao = gachaItemDao;
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

    gachaPlayer.addClosedGachaBall(getClosedGachaBalls(gachaPlayer.getId()));

    gachaPlayer.addGachaBall(getGachaBalls(gachaPlayer.getId()));

    var gachaItemIdSet = getOpenedGachaBalls(gachaPlayer.getId())
      .stream()
      .map(GachaBall::getGachaItemId)
      .map(Identifier::getValue)
      .collect(Collectors.toSet());

    var conditionItem = new GachaItemEntity();
    conditionItem.setGachaPlayerId(entity.getId());
    var gachaItemEntities = gachaItemDao.findAll(Example.of(conditionItem));
    Set<GachaItem> gachaItems = gachaItemEntities
      .stream()
      .filter(
        gachaItemEntity -> {
          return gachaItemIdSet.contains(gachaItemEntity.getId());
        }
      )
      .map(
        gachaItemEntity -> {
          return new GachaItem(
            new Identifier(gachaItemEntity.getId()),
            gachaItemEntity.getName(),
            gachaItemEntity.getDescription(),
            Rarity.toRarity(gachaItemEntity.getRarity()),
            new Identifier(gachaItemEntity.getGachaPlayerId())
          );
        }
      )
      .collect(Collectors.toSet());

    gachaPlayer.addGachaItem(gachaItems);
    return gachaPlayer;
  }

  private Set<ClosedGachaBall> getClosedGachaBalls(Identifier gachaPlayerId) {
    var condition = new GachaBallEntity();
    condition.setGachaPlayerId(gachaPlayerId.getValue());
    condition.setIsEjected(false);
    condition.setIsOpened(false);
    var gachaBallEntities = gachaBallDao.findAll(Example.of(condition));
    return gachaBallEntities
      .stream()
      .map(
        gachaBallEntity -> {
          return new ClosedGachaBall(
            new Identifier(gachaBallEntity.getId()),
            new Identifier(gachaBallEntity.getGachaPlayerId())
          );
        }
      )
      .collect(Collectors.toSet());
  }

  private Set<GachaBall> getGachaBalls(Identifier gachaPlayerId) {
    var condition = new GachaBallEntity();
    condition.setGachaPlayerId(gachaPlayerId.getValue());
    condition.setIsEjected(true);
    condition.setIsOpened(false);
    var gachaBallEntities = gachaBallDao.findAll(Example.of(condition));
    return gachaBallEntities
      .stream()
      .map(
        gachaBallEntity -> {
          return new GachaBall(
            new Identifier(gachaBallEntity.getId()),
            new Identifier(gachaBallEntity.getGachaItemId()),
            new Identifier(gachaBallEntity.getGachaPlayerId())
          );
        }
      )
      .collect(Collectors.toSet());
  }

  private Set<GachaBall> getOpenedGachaBalls(Identifier gachaPlayerId) {
    var condition = new GachaBallEntity();
    condition.setGachaPlayerId(gachaPlayerId.getValue());
    condition.setIsEjected(true);
    condition.setIsOpened(true);
    var gachaBallEntities = gachaBallDao.findAll(Example.of(condition));
    return gachaBallEntities
      .stream()
      .map(
        gachaBallEntity -> {
          return new GachaBall(
            new Identifier(gachaBallEntity.getId()),
            new Identifier(gachaBallEntity.getGachaItemId()),
            new Identifier(gachaBallEntity.getGachaPlayerId())
          );
        }
      )
      .collect(Collectors.toSet());
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
