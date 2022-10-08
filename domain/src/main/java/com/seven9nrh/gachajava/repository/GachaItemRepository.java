package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.Identifier;

public interface GachaItemRepository {
  void save(GachaItem gachaItem);

  GachaItem findById(Identifier id);
}
