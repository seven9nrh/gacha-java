package com.seven9nrh.gachajava.service;

import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;

public interface GachaService {
  GachaPlayer newGachaPlayer(String name, String description, int wallet);

  GachaPlayer buyGachaBalls(String id, Integer qty);

  GachaPlayer getGachaPlayer(String id);

  ClosedGachaBall pullGachaBall(String id);

  GachaBall openGachaBall(String id);

  GachaItem getGachaItem(String id);
}
