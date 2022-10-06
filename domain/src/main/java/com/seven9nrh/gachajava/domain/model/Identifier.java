package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;
import lombok.Value;

@Value
public class Identifier implements Serializable {

  private String value;

  public static Identifier generate() {
    return new Identifier(java.util.UUID.randomUUID().toString());
  }
}
