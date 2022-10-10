package com.seven9nrh.gachajava.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GachajavaWebfluxSecurityConfig {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(
    ServerHttpSecurity http,
    AuthenticationManager authenticationManager,
    SecurityContextRepository securityContextRepository
  ) {
    http
      .authorizeExchange()
      .pathMatchers(
        "/api/v1/management/auth/signin",
        "/api/v1/player/auth/signin"
      )
      .permitAll()
      .pathMatchers("/api/v1/management/**")
      .hasRole("MANAGER")
      .pathMatchers("/api/v1/player/**")
      .hasRole("PLAYER")
      .anyExchange()
      .authenticated()
      .and()
      .logout()
      .and()
      .csrf()
      .disable()
      .authenticationManager(authenticationManager)
      .securityContextRepository(securityContextRepository);
    return http.build();
  }
}
