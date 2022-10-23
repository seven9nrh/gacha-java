package com.seven9nrh.gachajava.site.controller;

import com.seven9nrh.gachajava.domain.GachaPlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site/player")
public class SitePlayerController {

  @Autowired
  GachaPlayerManager gachaPlayerManager;

  @RequestMapping("/game")
  public String index(Model model) {
    return "game";
  }
}
