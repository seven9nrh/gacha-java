package com.seven9nrh.gachajava.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.seven9nrh.gachajava.domain.model.ItemData;
import com.seven9nrh.gachajava.domain.model.Rarity;
import com.seven9nrh.gachajava.repository.ItemDataRepository;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ItemManagerTest {

  @InjectMocks
  ItemDataManager itemManager;

  @Mock
  ItemDataRepository itemDataRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetItemData() {
    // given

    int expected = 1;

    // when
    when(itemDataRepository.getAllItemData())
      .thenReturn(Set.of(new ItemData("item1", "description", Rarity.R)));

    Set<ItemData> actual = itemManager.getAllItemData();

    // then
    assertEquals(expected, actual.size());
  }
}
