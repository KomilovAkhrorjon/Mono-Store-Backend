package com.qiroldev.monomarket.utils;

import com.qiroldev.monomarket.product.system.enums.Lang;
import com.qiroldev.monomarket.utils.message.model.MessageModel;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtilsImpl implements Utils {

  private final HttpServletRequest request;

  public Map<String, String> getOauthHeaders() {
    try {
      HttpServletRequest httpRequest = request;
      Enumeration<String> headerNames = httpRequest.getHeaderNames();

      Map<String, String> map = new HashMap<>();

      if (headerNames != null) {
        while (headerNames.hasMoreElements()) {
          String val = headerNames.nextElement();
          map.put(val, httpRequest.getHeader(val));
        }
      }
//      map.put("authorization", "Bearer ");
      map.remove("content-length");
      return map;
    } catch (Exception e) {
      log.error("Legal Reference Service Component oauth headers method errors: {}", e.toString());
    }
    return new HashMap<>();
  }

  @Override
  public String getMessage(MessageModel model) {
    return model.getUz();
  }

  @Override
  public String generateRandomNumber(int n) {

    StringBuilder numbers = new StringBuilder();

    Random random = new Random();

    for (int i = 0; i < n; i++) {
      int randomNumber = random.nextInt(10); // Generates a random integer
      if (i == 0 && randomNumber == 0) {
        i--;
        continue;
      }
      numbers.append(randomNumber);
    }

    return numbers.toString();
  }

  @Override
  public Lang getLang() {

    var headers = getOauthHeaders();

    if (headers.containsKey("lang")) {
      try {
        return Lang.valueOf(headers.get("lang").toUpperCase());
      } catch (Exception e) {
        return Lang.UZ;
      }
    }

    return Lang.UZ;
  }

}
