package com.seven9nrh.gachajava.domain.model;

public class Identifier {

  private final String id;

  public Identifier(String id) {
    this.id = id;
  }

  public Identifier() {
    this.id = createId();
  }

  public String getId() {
    return id;
  }

  public String createId() {
    return java.util.UUID.randomUUID().toString();
  }
}
