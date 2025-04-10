package com.qiroldev.monomarket.external.eskiz;

import com.qiroldev.monomarket.external.eskiz.dto.SmsRequest;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EskizSmsComponent {

  private final EskizClient eskizClient;

  private Map<String, String> getHeaders() {
    return Map.of(
        "Authorization",
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDUzMTg4OTksImlhdCI6MTc0MjcyNjg5OSwicm9sZSI6InRlc3QiLCJzaWduIjoiM2ZjNjZhODZkZmUwY2Y4MzM3OGE0NTI4YTlmMzE0NjAxMjdjNTFmNTk2YTEyMWNjMzM0OTRhY2IyNDMzYzk3YyIsInN1YiI6IjY1NjUifQ.XbtDn82s2i0UtFoso_B5MOeQaBiReulx73w4gzu3UJ0"
    );
  }


  public void sendSms(SmsRequest smsRequest) {
    log.info("Sending sms to {} with message: {}", smsRequest.getPhoneNumber(), smsRequest.getMessage());

    try {
      eskizClient.send(getHeaders(), smsRequest.getPhoneNumber(), smsRequest.getMessage(), "monostore.uz");
    }catch (Exception e) {
      log.error("Error while sending sms to {} with message: {}", smsRequest.getPhoneNumber(), smsRequest.getMessage());
      throw new BadRequestException("sms_send_error");
    }
  }

}
