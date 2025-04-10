package com.qiroldev.monomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MonoMarketApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonoMarketApplication.class, args);
  }

}
