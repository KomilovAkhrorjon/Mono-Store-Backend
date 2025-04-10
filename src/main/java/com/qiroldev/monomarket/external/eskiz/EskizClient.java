package com.qiroldev.monomarket.external.eskiz;

import feign.Headers;
import feign.Param;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(
    name = "eskiz-client",
    url = "https://notify.eskiz.uz/api/message/sms",
    configuration = EskizClient.FeignConfig.class)
public interface EskizClient {

  @PostMapping(value = "/send", consumes = "multipart/form-data")
  void send(
      @RequestHeader Map<String, String> headers,
      @RequestPart("mobile_phone") String mobilePhone,
      @RequestPart("message") String message,
      @RequestPart("from") String from);


  class FeignConfig {

    @Bean
    public Encoder feignFormEncoder() {
      return new SpringFormEncoder(); // Use SpringFormEncoder for multipart/form-data
    }
  }
}
