package com.seven9nrh.gachajava.domain;

import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.repository.GachaBallRepository;
import com.seven9nrh.gachajava.repository.GachaItemRepository;
import com.seven9nrh.gachajava.repository.GachaPlayerRepository;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class GachaPlayerManager {

  private final GachaPlayerRepository gachaPlayerRepository;

  private final GachaBallManager gachaBallManager;

  private final GachaBallRepository gachaBallRepository;

  private final GachaItemRepository gachaItemRepository;

  public GachaPlayerManager(
    GachaPlayerRepository gachaPlayerRepository,
    GachaBallManager gachaBallManager,
    GachaBallRepository gachaBallRepository,
    GachaItemRepository gachaItemRepository
  ) {
    this.gachaPlayerRepository = gachaPlayerRepository;
    this.gachaBallManager = gachaBallManager;
    this.gachaBallRepository = gachaBallRepository;
    this.gachaItemRepository = gachaItemRepository;
  }

  public void saveGachaPlayer(GachaPlayer gachaPlayer) {
    gachaPlayerRepository.save(gachaPlayer);
  }

  public void deleteGachaPlayer(Identifier id) {
    gachaPlayerRepository.delete(id);
  }

  public GachaPlayer buyGachaBalls(Identifier id, int qty) {
    GachaPlayer gachaPlayer = gachaPlayerRepository.findById(id);

    var balls = gachaBallManager.makeBalls(qty, gachaPlayer);
    if (!balls.isEmpty()) {
      gachaPlayer.addClosedGachaBall(balls);
    }

    return gachaPlayer;
  }

  public GachaPlayer newGachaPlayer(
    String name,
    String description,
    int wallet
  ) {
    GachaPlayer gachaPlayer = new GachaPlayer(name, description, wallet);

    saveGachaPlayer(gachaPlayer);

    return gachaPlayer;
  }

  public GachaPlayer findById(Identifier id) {
    return gachaPlayerRepository.findById(id);
  }

  public ClosedGachaBall ejectGachaBall(Identifier id) {
    GachaPlayer gachaPlayer = gachaPlayerRepository.findById(id);
    Set<ClosedGachaBall> gachaBalls = gachaPlayer.getClosedGachaBalls();
    if (gachaBalls.isEmpty()) {
      return null;
    }
    var gachaBall = gachaBalls.iterator().next();
    gachaBalls.remove(gachaBall);
    return gachaBallRepository.ejectGachaBall(gachaBall.getId());
  }

  public GachaBall openGachaBall(Identifier id) {
    GachaBall gachaBall = gachaBallRepository.findById(id);
    if (gachaBall == null) {
      return null;
    }
    return gachaBallManager.openGachaBall(gachaBall);
  }

  public GachaItem getGachaItem(Identifier id) {
    return gachaItemRepository.findById(id);
  }
}
