package com.seven9nrh.gachajava.domain.model;

import com.seven9nrh.gachajava.domain.GachaBallAlreadyOpenedException;
import java.io.Serializable;

public class GachaBall implements Serializable {

  private final Identifier id;
  private GachaItem item;

  private boolean isOpenned;

  public GachaBall(GachaItem item) {
    this.id = new Identifier();
    this.item = item;
    this.isOpenned = false;
  }

  public Identifier getIdentifier() {
    return id;
  }

  public GachaItem getItem() {
    if (this.isOpenned) {
      throw new GachaBallAlreadyOpenedException(
        "Gacha ball is already opened."
      );
    } else {
      this.isOpenned = true;
      return item;
    }
  }

  public boolean isOpenned() {
    return isOpenned;
  }

  public void setItem(GachaItem item) {
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
