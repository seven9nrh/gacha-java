package com.seven9nrh.gachajava.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Item;
import com.seven9nrh.gachajava.domain.model.Rarity;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @Disabled
  void testMakeBalls() {
    // given
    int count = 10;

    int expected = 10;

    // when
    var itemSet = new HashSet<Item>();
    itemSet.add(new Item("item1", "description", Rarity.N));
    when(itemManager.getAllItem()).thenReturn(itemSet);

    Set<ClosedGachaBall> actual = gachaBallMaker.makeBalls(
      count,
      new GachaPlayer("hoge", "name", 11)
    );

    // then
    assertEquals(expected, actual.size());
  }
}
