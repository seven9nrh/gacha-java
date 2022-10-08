package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.Identifier;

public interface GachaBallRepository {
  void save(GachaBall gachaBall);

  void deleteById(Identifier id);
}
