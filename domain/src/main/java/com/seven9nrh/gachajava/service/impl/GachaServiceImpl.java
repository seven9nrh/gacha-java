package com.seven9nrh.gachajava.service.impl;

import com.seven9nrh.gachajava.domain.GachaPlayerManager;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.service.GachaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GachaServiceImpl implements GachaService {

  GachaPlayerManager gachaPlayerManager;

  public GachaServiceImpl(GachaPlayerManager gachaPlayerManager) {
    this.gachaPlayerManager = gachaPlayerManager;
  }

  @Override
  public GachaPlayer newGachaPlayer(
    String name,
    String description,
    int wallet
  ) {
    return gachaPlayerManager.newGachaPlayer(name, description, wallet);
  }

  @Override
  public GachaPlayer buyGachaBalls(String id, Integer qty) {
    return gachaPlayerManager.buyGachaBalls(new Identifier(id), qty);
  }

  @Override
  public GachaPlayer getGachaPlayer(String id) {
    return gachaPlayerManager.findById(new Identifier(id));
  }

  @Override
  public ClosedGachaBall pullGachaBall(String id) {
    return gachaPlayerManager.ejectGachaBall(new Identifier(id));
  }

  @Override
  public GachaBall openGachaBall(String id) {
    return gachaPlayerManager.openGachaBall(new Identifier(id));
  }

  @Override
  public GachaItem getGachaItem(String id) {
    return gachaPlayerManager.getGachaItem(new Identifier(id));
  }
}
