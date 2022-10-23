package com.seven9nrh.gachajava.site.controller;

import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.service.GachaService;
import com.seven9nrh.gachajava.site.form.GachaPlayerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site/manager")
public class SiteManagerController {

  @Autowired
  GachaService gachaService;

  @RequestMapping("/game")
  public String index(Model model) {
    model.addAttribute(new GachaPlayerForm());
    return "game";
  }

  @PostMapping("/gacha-player/new")
  public String newGachaPlayer(
    Model model,
    @Validated @ModelAttribute GachaPlayerForm form,
    BindingResult result
  ) {
    if (result.hasErrors()) {
      return "game";
    }
    GachaPlayer newGachaPlayer = gachaService.newGachaPlayer(
      form.getPlayerName(),
      form.getPlayerDescription(),
      form.getPlayerWallet()
    );
    model.addAttribute("gachaPlayer", newGachaPlayer);
    form.setGachaPlayerId(newGachaPlayer.getId().getValue());
    return "game";
  }

  @PostMapping("/gacha-player/buy")
  public String buyGachaBalls(
    Model model,
    @Validated @ModelAttribute GachaPlayerForm form,
    BindingResult result
  ) {
    if (result.hasErrors()) {
      return "game";
    }
    GachaPlayer buyGachaBalls = gachaService.buyGachaBalls(
      form.getGachaPlayerId(),
      10
    );
    model.addAttribute("gachaPlayer", buyGachaBalls);
    return "game";
  }

  @PostMapping("/gacha-player/pull")
  public String pullGachaBalls(
    Model model,
    @Validated @ModelAttribute GachaPlayerForm form,
    BindingResult result
  ) {
    if (result.hasErrors()) {
      return "game";
    }
    gachaService.pullGachaBall(form.getGachaPlayerId());

    GachaPlayer gachaPlayer = gachaService.getGachaPlayer(
      form.getGachaPlayerId()
    );

    model.addAttribute("gachaPlayer", gachaPlayer);
    return "game";
  }

  @PostMapping("/gacha-player/open/{gachaBallId}")
  public String openGachaBalls(
    Model model,
    @PathVariable("gachaBallId") String gachaBallId,
    @Validated @ModelAttribute GachaPlayerForm form,
    BindingResult result
  ) {
    if (result.hasErrors()) {
      return "game";
    }
    gachaService.openGachaBall(gachaBallId);

    GachaPlayer gachaPlayer = gachaService.getGachaPlayer(
      form.getGachaPlayerId()
    );

    model.addAttribute("gachaPlayer", gachaPlayer);
    return "game";
  }
}
