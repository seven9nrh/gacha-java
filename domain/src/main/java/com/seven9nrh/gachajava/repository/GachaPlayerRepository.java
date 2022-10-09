package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;

public interface GachaPlayerRepository {
  void save(GachaPlayer gachaPlayer);

  void delete(Identifier id);

  GachaPlayer findById(Identifier id);
}
