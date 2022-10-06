package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.Identifier;

public interface GachaMachineRepository {
  void save(GachaMachine gachaMachine);

  void delete(Identifier id);

  GachaMachine getGachaMachine(Identifier id);
}
