package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClosedGachaBall implements Serializable {

  private Identifier id;

  private Identifier gachaPlayerId;
}
