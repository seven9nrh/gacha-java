package com.seven9nrh.gachajava.repository;

import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.GachaTable;
import java.util.Set;

public interface GachaTableRepository {
  Set<GachaTable> getGachaTable(GachaItemCategory category);
}
