package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaMachine;

public interface GachaMachineRepository {
  void save(GachaMachine gachaMachine);

  void delete(String id);

  GachaMachine findById(String id);
}
