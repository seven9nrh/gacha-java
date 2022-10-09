package com.seven9nrh.gachajava.application.service.impl;

import com.seven9nrh.gachajava.application.api.v1.body.GachaPlayerBody;
import com.seven9nrh.gachajava.application.service.GachaService;
import com.seven9nrh.gachajava.domain.GachaPlayerManager;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class GachaServiceImpl implements GachaService {

  GachaPlayerManager gachaPlayerManager;

  public GachaServiceImpl(GachaPlayerManager gachaPlayerManager) {
    this.gachaPlayerManager = gachaPlayerManager;
  }

  @Override
  public GachaPlayer newGachaPlayer(GachaPlayerBody form) {
    return gachaPlayerManager.newGachaPlayer(
      form.getName(),
      form.getDescription(),
      form.getWallet()
    );
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
    return gachaPlayerManager.pullGachaBall(new Identifier(id));
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
