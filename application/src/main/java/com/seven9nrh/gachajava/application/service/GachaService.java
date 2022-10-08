package com.seven9nrh.gachajava.application.service;

import com.seven9nrh.gachajava.application.api.body.GachaMachineBody;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaMachine;

public interface GachaService {
  GachaMachine newGachaMachine(GachaMachineBody form);

  GachaMachine refillGachaBalls(String id, Integer qty);

  GachaMachine getGachaMachine(String id);

  ClosedGachaBall pullGachaBall(String id);

  GachaBall openGachaBall(String id);
}
