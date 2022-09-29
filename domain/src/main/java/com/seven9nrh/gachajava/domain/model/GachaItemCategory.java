package com.seven9nrh.gachajava.domain.model;

import java.io.Serializable;

public class GachaItemCategory implements Serializable {

  private final Identifier id;
  private final String name;
  private final String description;

  public GachaItemCategory(String name, String description) {
    this.id = new Identifier();
    this.name = name;
    this.description = description;
  }

  public Identifier getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return (
      "GachaItemCategory [id=" +
      id +
      ", name=" +
      name +
      ", description=" +
      description +
      "]"
    );
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result =
      prime * result + ((description == null) ? 0 : description.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    GachaItemCategory other = (GachaItemCategory) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (description == null) {
      if (other.description != null) return false;
    } else if (!description.equals(other.description)) return false;
    return true;
  }
}
