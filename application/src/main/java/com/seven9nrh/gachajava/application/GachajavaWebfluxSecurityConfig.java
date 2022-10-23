package com.seven9nrh.gachajava.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class GachajavaWebfluxSecurityConfig {

  @Bean
  @Order(1)
  public SecurityWebFilterChain springSecurityFilterChain1(
    ServerHttpSecurity http
  ) {
    http
      .formLogin()
      .loginPage("/site/signin")
      .authenticationSuccessHandler(
        new RedirectServerAuthenticationSuccessHandler("/site/login")
      )
      .authenticationFailureHandler(
        new RedirectServerAuthenticationFailureHandler("/site/signin/error")
      )
      .and()
      .authorizeExchange()
      .pathMatchers(
        "/site/signin",
        "/css/**",
        "/js/**",
        "/img/**",
        "/webjars/**",
        "/favicon.ico",
        "/error",
        "/error/**",
        "/actuator/**"
      )
      .permitAll()
      .pathMatchers("/site/manager/**")
      .hasRole("MANAGER")
      .pathMatchers("/site/player/**")
      .hasRole("PLAYER")
      .anyExchange()
      .permitAll()
      .and()
      .logout()
      .and()
      .csrf()
      .disable();
    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityWebFilterChain springSecurityFilterChain2(
    ServerHttpSecurity http
  ) {
    http
      .formLogin()
      .disable()
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
      .disable();
    return http.build();
  }
}
