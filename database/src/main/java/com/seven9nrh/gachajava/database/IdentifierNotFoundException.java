package com.seven9nrh.gachajava.database;

import com.seven9nrh.gachajava.domain.model.Identifier;

public class IdentifierNotFoundException extends RuntimeException {

  public IdentifierNotFoundException(String id) {
    super("Id not found: " + id);
  }

  public IdentifierNotFoundException(Identifier id) {
    super("Id not found: " + id.getValue());
  }
}
