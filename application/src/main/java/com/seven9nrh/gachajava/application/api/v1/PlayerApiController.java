package com.seven9nrh.gachajava.application.api.v1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.seven9nrh.gachajava.application.service.GachaService;
import com.seven9nrh.gachajava.domain.model.ClosedGachaBall;
import com.seven9nrh.gachajava.domain.model.GachaBall;
import com.seven9nrh.gachajava.domain.model.GachaItem;
import com.seven9nrh.gachajava.domain.model.GachaPlayer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerApiController {

  private final GachaService gachaService;

  public PlayerApiController(GachaService gachaService) {
    this.gachaService = gachaService;
  }

  @PostMapping("/auth/signin")
  public ResponseEntity<Void> signin() {
    String token = JWT
      .create()
      .withSubject("player")
      .withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC))
      .sign(Algorithm.HMAC256("secret"));
    ResponseCookie jwtCookie = ResponseCookie
      .from("jwt", token)
      .path("/api/v1/player")
      .maxAge(24 * 60 * 60)
      .httpOnly(true)
      .build();
    return ResponseEntity
      .ok()
      .header("Set-Cookie", jwtCookie.toString())
      .build();
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
