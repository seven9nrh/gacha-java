package com.seven9nrh.gachajava.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    String authToken = authentication.getCredentials().toString();
    DecodedJWT decode = JWT.decode(authToken);
    String subject = decode.getSubject();
    if (decode.getExpiresAt() == null) {
      return Mono.empty();
    }
    LocalDateTime exp = decode
      .getExpiresAt()
      .toInstant()
      .atZone(ZoneOffset.UTC)
      .toLocalDateTime();
    LocalDateTime now = LocalDateTime.now();
    if (exp.isBefore(now)) {
      return Mono.empty();
    }
    if (subject.equals("manager")) {
      return Mono.just(
        new UsernamePasswordAuthenticationToken(
          subject,
          authToken,
          List.of(new SimpleGrantedAuthority("ROLE_MANAGER"))
        )
      );
    } else if (subject.equals("player")) {
      return Mono.just(
        new UsernamePasswordAuthenticationToken(
          subject,
          authToken,
          List.of(new SimpleGrantedAuthority("ROLE_PLAYER"))
        )
      );
    } else {
      return Mono.empty();
    }
  }
}
