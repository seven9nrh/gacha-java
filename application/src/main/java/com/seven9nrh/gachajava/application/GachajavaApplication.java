package com.seven9nrh.gachajava.application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
  basePackages = {
    "com.seven9nrh.gachajava.application",
    "com.seven9nrh.gachajava.service",
    "com.seven9nrh.gachajava.domain",
    "com.seven9nrh.gachajava.database",
    "com.seven9nrh.gachajava.site",
  }
)
@OpenAPIDefinition(
  info = @io.swagger.v3.oas.annotations.info.Info(
    title = "GachaJava",
    version = "1.0"
  )
)
public class GachajavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(GachajavaApplication.class, args);
  }
}
