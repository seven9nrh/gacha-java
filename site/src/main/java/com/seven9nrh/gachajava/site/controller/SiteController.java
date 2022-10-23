package com.seven9nrh.gachajava.site.controller;

import com.seven9nrh.gachajava.site.form.SigninForm;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class SiteController {

  @GetMapping({ "/signin", "/signin/{error}" })
  public String signin(
    Model model,
    @PathVariable(required = false) String error
  ) {
    SigninForm form = new SigninForm();
    form.setUsername("manager");
    model.addAttribute(form);
    model.addAttribute(
      "error",
      "error".equalsIgnoreCase(error) ? "Invalid username." : ""
    );
    model.addAttribute("radioUsername", initRadioUsername());
    return "signin";
  }

  @GetMapping("/login")
  public String login(Model model, @AuthenticationPrincipal String authToken) {
    return "redirect:/site/" + authToken + "/game";
  }

  private Map<String, String> initRadioUsername() {
    Map<String, String> radio = new LinkedHashMap<>();
    radio.put("manager", "manager");
    radio.put("player", "player");
    return radio;
  }
}
