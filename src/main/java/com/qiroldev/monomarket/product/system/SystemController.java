package com.qiroldev.monomarket.product.system;

import com.qiroldev.monomarket.product.system.enums.Lang;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/v0/system")
public class SystemController {

  @GetMapping("/languages")
  public ResponseEntity<?> getLangs() {
    return ResponseEntity.ok(Lang.values());
  }

}
