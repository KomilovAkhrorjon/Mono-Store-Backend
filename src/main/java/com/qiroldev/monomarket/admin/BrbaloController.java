package com.qiroldev.monomarket.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v0/admin")
public class BrbaloController {

  @GetMapping("/test")
  ResponseEntity<?> testClazz() {

    return ResponseEntity.ok(123);
  }

}
