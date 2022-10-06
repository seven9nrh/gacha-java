package com.seven9nrh.gachajava.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
  basePackages = {
    "com.seven9nrh.gachajava.application",
    "com.seven9nrh.gachajava.domain",
    "com.seven9nrh.gachajava.database",
  }
)
public class GachajavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(GachajavaApplication.class, args);
  }
}
