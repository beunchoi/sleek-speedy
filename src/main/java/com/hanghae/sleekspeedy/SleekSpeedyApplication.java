package com.hanghae.sleekspeedy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SleekSpeedyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SleekSpeedyApplication.class, args);
  }

}
