package com.seven9nrh.gachajava.application.api.v1.body;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemBody {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private String rarity;
}
