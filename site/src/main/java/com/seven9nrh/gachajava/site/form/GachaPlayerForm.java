package com.seven9nrh.gachajava.site.form;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Data
public class GachaPlayerForm {

  @NotNull
  @Length(min = 1, max = 50)
  private String playerName;

  @NotNull
  @Length(min = 1, max = 200)
  private String playerDescription;

  @NotNull
  @NumberFormat(style = NumberFormat.Style.NUMBER)
  private Integer playerWallet;

  private String gachaPlayerId;

  private String gachaBallId;
}
