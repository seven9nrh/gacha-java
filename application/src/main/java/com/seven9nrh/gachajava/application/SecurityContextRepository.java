package com.seven9nrh.gachajava.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository
  implements ServerSecurityContextRepository {

  private static final String TOKEN_PREFIX = "Bearer ";

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Mono<SecurityContext> load(ServerWebExchange swe) {
    ServerHttpRequest request = swe.getRequest();

    request
      .getCookies()
      .forEach(
        (key, value) -> {
          if (key.equals("jwt")) {
            request
              .mutate()
              .header(
                HttpHeaders.AUTHORIZATION,
                TOKEN_PREFIX + value.get(0).getValue()
              );
          }
        }
      );
    String authHeader = request
      .getHeaders()
      .getFirst(HttpHeaders.AUTHORIZATION);
    String authToken = null;
    if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
      authToken = authHeader.replace(TOKEN_PREFIX, "");
    }
    if (authToken != null) {
      Authentication auth = new UsernamePasswordAuthenticationToken(
        authToken,
        authToken
      );
      return this.authenticationManager.authenticate(auth)
        .map(authentication -> new SecurityContextImpl(authentication));
    } else {
      return Mono.empty();
    }
  }
}
