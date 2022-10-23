package com.seven9nrh.gachajava.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenGeneretor {

  private static final String SECRET = "secret";

  private TokenGeneretor() {
    super();
  }

  public static String generate(String username) {
    return JWT
      .create()
      .withSubject(username)
      .withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC))
      .sign(Algorithm.HMAC256(SECRET));
  }
}
