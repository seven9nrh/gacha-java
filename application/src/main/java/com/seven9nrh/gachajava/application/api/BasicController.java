package com.seven9nrh.gachajava.application.api;

import com.seven9nrh.gachajava.application.api.body.GachaMachineBody;
import com.seven9nrh.gachajava.application.api.body.ItemDataBody;
import com.seven9nrh.gachajava.application.service.GachaService;
import com.seven9nrh.gachajava.application.service.ItemDataService;
import com.seven9nrh.gachajava.domain.model.GachaMachine;
import com.seven9nrh.gachajava.domain.model.ItemData;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BasicController {

  private final GachaService gachaService;

  private final ItemDataService itemDataService;

  public BasicController(
    GachaService gachaService,
    ItemDataService itemDataService
  ) {
    this.gachaService = gachaService;
    this.itemDataService = itemDataService;
  }

  @PostMapping("/gacha-machine")
  public GachaMachine newGachaMachine(
    @Validated @RequestBody GachaMachineBody form
  ) {
    return gachaService.newGachaMachine(form);
  }

  @PutMapping("/gacha-machine/{id}/refill/{qty}")
  public GachaMachine refill(
    @PathVariable(name = "id") String id,
    @PathVariable(name = "qty") Integer qty
  ) {
    return gachaService.refillGachaBalls(id, qty);
  }

  @GetMapping("/gacha-machine/{id}")
  public GachaMachine getGachaMachine(@PathVariable(name = "id") String id) {
    return gachaService.getGachaMachine(id);
  }

  @PostMapping("/item")
  public ItemData newItem(@Validated @RequestBody ItemDataBody form) {
    return itemDataService.newItemData(form);
  }

  @GetMapping("/item/{id}")
  public ItemData getItem(@PathVariable(name = "id") String id) {
    return itemDataService.getItemData(id);
  }

  @PutMapping(value = "/item/{id}")
  public ItemData modifyItemData(
    @PathVariable String id,
    @RequestBody ItemDataBody form
  ) {
    return itemDataService.modifyItemData(id, form);
  }
}
