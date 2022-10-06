package com.seven9nrh.gachajava.database.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.seven9nrh.gachajava.database")
@EntityScan(basePackages = "com.seven9nrh.gachajava.database.entity")
public class DababaseConfig {}
