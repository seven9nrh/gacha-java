package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GachaBall implements Serializable {

  private Identifier id;

  private Identifier gachaItemId;

  private Identifier gachaMachineId;

  public GachaBall(Identifier gachaItemId, Identifier gachaMachineId) {
    this.id = Identifier.generate();
    this.gachaItemId = gachaItemId;
    this.gachaMachineId = gachaMachineId;
  }
}
