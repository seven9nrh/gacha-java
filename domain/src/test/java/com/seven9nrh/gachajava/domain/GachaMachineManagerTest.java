package com.seven9nrh.gachajava.domain;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.GachaItem;
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
    Identifier id = new Identifier("id");

    // when
    gachaMachineManager.deleteGachaMachine(id);

    // then
    verify(gachaMachineRepository).delete(id);
  }

  @Test
  void testRefillGachaBalls() {
    // given
    Identifier id = new Identifier("id");
    int qty = 10;

    int price = 100;
    int maxStock = 50;

    String name = "name";
    String description = "description";

    // when
    when(gachaMachineRepository.getGachaMachine(id))
      .thenReturn(new GachaMachine(id, name, description, price, maxStock));

    Set balls = new HashSet<>();
    balls.add(new GachaItem(name, description, Rarity.N));
    balls.add(new GachaItem(name, description, Rarity.N));
    balls.add(new GachaItem(name, description, Rarity.N));
    balls.add(new GachaItem(name, description, Rarity.N));
    balls.add(new GachaItem(name, description, Rarity.N));
    when(gachaBallMaker.makeBalls(5)).thenReturn(balls);

    gachaMachineManager.refillGachaBalls(id, qty);
    // then

  }

  @Test
  void testSaveGachaMachine() {
    // given
    GachaMachine gachaMachine = new GachaMachine(
      Identifier.generate(),
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
