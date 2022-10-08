package com.seven9nrh.gachajava.application.api.v1;

import com.seven9nrh.gachajava.application.api.v1.body.GachaPlayerBody;
import com.seven9nrh.gachajava.application.api.v1.body.ItemBody;
import com.seven9nrh.gachajava.application.service.GachaService;
import com.seven9nrh.gachajava.application.service.ItemService;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import com.seven9nrh.gachajava.domain.model.Item;
import java.util.Set;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementApiController {

  private final GachaService gachaService;

  private final ItemService itemService;

  public ManagementApiController(
    GachaService gachaService,
    ItemService itemService
  ) {
    this.gachaService = gachaService;
    this.itemService = itemService;
  }

  @PostMapping("/gacha-player")
  public GachaPlayer newGachaPlayer(
    @Validated @RequestBody GachaPlayerBody form
  ) {
    return gachaService.newGachaPlayer(form);
  }

  @PutMapping("/gacha-player/{id}/buy/{qty}")
  public GachaPlayer refill(
    @PathVariable(name = "id") String id,
    @PathVariable(name = "qty") Integer qty
  ) {
    return gachaService.buyGachaBalls(id, qty);
  }

  @GetMapping("/gacha-player/{id}")
  public GachaPlayer getGachaPlayer(@PathVariable(name = "id") String id) {
    return gachaService.getGachaPlayer(id);
  }

  @PostMapping("/item")
  public Item newItem(@Validated @RequestBody ItemBody form) {
    return itemService.newItem(form);
  }

  @GetMapping("/item/{id}")
  public Item getItem(@PathVariable(name = "id") String id) {
    return itemService.getItem(id);
  }

  @GetMapping("/item")
  public Set<Item> getItemList() {
    return itemService.getItemList();
  }

  @PutMapping(value = "/item/{id}")
  public Item modifyItem(@PathVariable String id, @RequestBody ItemBody form) {
    return itemService.modifyItem(id, form);
  }

  @PutMapping("/gacha-player/{id}/pull-gacha-ball")
  public ClosedGachaBall pullGachaBall(@PathVariable(name = "id") String id) {
    return gachaService.pullGachaBall(id);
  }

  @PutMapping("/gacha-ball/{id}/open")
  public GachaBall openGachaBall(@PathVariable(name = "id") String id) {
    return gachaService.openGachaBall(id);
  }

  @GetMapping("/gacha-item/{id}")
  public GachaItem getGachaItem(@PathVariable(name = "id") String id) {
    return gachaService.getGachaItem(id);
  }
}
