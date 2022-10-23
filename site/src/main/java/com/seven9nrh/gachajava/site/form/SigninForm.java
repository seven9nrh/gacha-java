package com.seven9nrh.gachajava.site.form;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SigninForm {

  @NotNull
  private String username;
}
