package com.seven9nrh.gachajava.domain.model;

import com.seven9nrh.gachajava.domain.GachaBallAlreadyOpenedException;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class GachaBall implements Serializable {

  private Identifier id;

  private GachaItem item;

  private boolean isOpenned;

  public GachaBall(GachaItem item) {
    this.id = Identifier.generate();
    this.item = item;
    this.isOpenned = false;
  }

  public GachaItem takeOutItem() {
    if (this.isOpenned) {
      throw new GachaBallAlreadyOpenedException(
        "Gacha ball is already opened."
      );
    } else {
      this.isOpenned = true;
      return item;
    }
  }

  public void setNewItem(GachaItem item) {
    if (this.isOpenned) {
      this.item = item;
      this.isOpenned = false;
    } else {
      throw new GachaBallAlreadyOpenedException(
        "Gacha ball is not opened yet."
      );
    }
  }
}
