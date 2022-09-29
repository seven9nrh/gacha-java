package com.seven9nrh.gachajava.domain;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaItemCategory;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.GachaMachineRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GachaMachineManagerTest {

  @InjectMocks
  GachaMachineManager gachaMachineManager;

  @Mock
  GachaMachineRepository gachaMachineRepository;

  @Mock
  GachaBallMaker gachaBallMaker;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDeleteGachaMachine() {
    // given
    String id = "id";

    // when
    gachaMachineManager.deleteGachaMachine(id);

    // then
    verify(gachaMachineRepository).delete(id);
  }

  @Test
  void testRefillGachaBalls() {
    // given
    String id = "id";
    int qty = 10;
    GachaItemCategory category = new GachaItemCategory("cate1", "description");

    int price = 100;
    int maxStock = 50;

    String name = "name";
    String description = "description";

    // when
    when(gachaMachineRepository.findById(id))
      .thenReturn(
        new GachaMachine(new Identifier(id), name, description, price, maxStock)
      );

    Set balls = new HashSet<>();
    balls.add(new GachaItem(name, description, Rarity.N, category));
    balls.add(new GachaItem(name, description, Rarity.N, category));
    balls.add(new GachaItem(name, description, Rarity.N, category));
    balls.add(new GachaItem(name, description, Rarity.N, category));
    balls.add(new GachaItem(name, description, Rarity.N, category));
    when(gachaBallMaker.makeBalls(category, 5)).thenReturn(balls);

    gachaMachineManager.refillGachaBalls(id, qty, category);
    // then
    // verify(gachaBallMaker).makeGachaBalls(id);
  }

  @Test
  void testSaveGachaMachine() {
    // given
    GachaMachine gachaMachine = new GachaMachine(
      new Identifier(),
      "name",
      "description",
      1,
      10
    );

    // when
    gachaMachineManager.saveGachaMachine(gachaMachine);

    // then
    verify(gachaMachineRepository).save(gachaMachine);
  }
}
