package com.seven9nrh.gachajava.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.GachaTable;
import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.GachaTableRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GachaBallMakerTest {

  // sut: system under test
  @InjectMocks
  GachaBallMaker gachaBallMaker;

  @Mock
  ItemManager itemManager;

  @Mock
  GachaTableRepository gachaTableRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testMakeBalls() {
    // given
    GachaItemCategory category = new GachaItemCategory("cate1", "description");
    int count = 10;

    int expected = 10;

    // when
    var itemDataSet = new HashSet<ItemData>();
    itemDataSet.add(new ItemData("item1", "description", Rarity.N));
    when(itemManager.getItemData(category)).thenReturn(itemDataSet);

    var gachaTableSet = new HashSet<GachaTable>();
    gachaTableSet.add(new GachaTable(Rarity.N, 10));
    gachaTableSet.add(new GachaTable(Rarity.R, 5));
    when(gachaTableRepository.getGachaTable(category))
      .thenReturn(gachaTableSet);

    Set<GachaBall> actual = gachaBallMaker.makeBalls(category, count);

    // then
    assertEquals(expected, actual.size());
  }
}
