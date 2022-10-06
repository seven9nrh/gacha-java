package com.seven9nrh.gachajava.application.api.body;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GachaMachineBody {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Integer price;

  @NotNull
  private Integer maxStock;
}
