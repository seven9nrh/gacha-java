package com.seven9nrh.gachajava.application.service;

import com.seven9nrh.gachajava.application.api.body.GachaMachineBody;
import com.seven9nrh.gachajava.domain.GachaMachineManager;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.Identifier;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class GachaServiceImpl implements GachaService {

  GachaMachineManager gachaMachineManager;

  public GachaServiceImpl(GachaMachineManager gachaMachineManager) {
    this.gachaMachineManager = gachaMachineManager;
  }

  @Override
  public GachaMachine newGachaMachine(GachaMachineBody form) {
    return gachaMachineManager.newGachaMachine(
      form.getName(),
      form.getDescription(),
      form.getPrice(),
      form.getMaxStock()
    );
  }

  @Override
  public GachaMachine refillGachaBalls(String id, Integer qty) {
    return gachaMachineManager.refillGachaBalls(new Identifier(id), qty);
  }

  @Override
  public GachaMachine getGachaMachine(String id) {
    return gachaMachineManager.getGachaMachine(new Identifier(id));
  }
}
