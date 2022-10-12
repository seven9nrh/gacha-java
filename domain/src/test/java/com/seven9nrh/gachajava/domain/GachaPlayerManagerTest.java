package com.seven9nrh.gachajava.domain;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Identifier;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.GachaPlayerRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GachaPlayerManagerTest {

  @InjectMocks
  GachaPlayerManager gachaPlayerManager;

  @Mock
  GachaPlayerRepository gachaPlayerRepository;

  @Mock
  GachaBallManager gachaBallManager;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDeleteGachaMachine() {
    // given
    Identifier id = new Identifier("id");

    // when
    gachaPlayerManager.deleteGachaPlayer(id);

    // then
    verify(gachaPlayerRepository).delete(id);
  }

  @Test
  void testRefillGachaBalls() {
    // given
    Identifier id = new Identifier("id");
    int qty = 10;

    int wallet = 100;

    String name = "name";
    String description = "description";

    // when
    when(gachaPlayerRepository.findById(id))
      .thenReturn(new GachaPlayer(id, name, description, wallet));

    Set balls = new HashSet<>();
    balls.add(new GachaItem(name, description, Rarity.N, id));
    balls.add(new GachaItem(name, description, Rarity.N, id));
    balls.add(new GachaItem(name, description, Rarity.N, id));
    balls.add(new GachaItem(name, description, Rarity.N, id));
    balls.add(new GachaItem(name, description, Rarity.N, id));
    when(
      gachaBallManager.makeBalls(
        5,
        new GachaPlayer(id, name, description, wallet)
      )
    )
      .thenReturn(balls);

    gachaPlayerManager.buyGachaBalls(id, qty);
    // then

  }

  @Test
  void testSaveGachaMachine() {
    // given
    GachaPlayer gachaMachine = new GachaPlayer(
      Identifier.generate(),
      "name",
      "description",
      1
    );

    // when
    gachaPlayerManager.saveGachaPlayer(gachaMachine);

    // then
    verify(gachaPlayerRepository).save(gachaMachine);
  }
}
