package com.seven9nrh.gachajava.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.Item;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.ItemRepository;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ItemManagerTest {

  @InjectMocks
  ItemManager itemManager;

  @Mock
  ItemRepository itemRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetItem() {
    // given

    int expected = 1;

    // when
    when(itemRepository.getAllItem())
      .thenReturn(Set.of(new Item("item1", "description", Rarity.R)));

    Set<Item> actual = itemManager.getAllItem();

    // then
    assertEquals(expected, actual.size());
  }
}
