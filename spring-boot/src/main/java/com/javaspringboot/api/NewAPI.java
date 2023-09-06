package com.javaspringboot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewAPI {
  @GetMapping(value = "/test")
  public String testAPI() {

      return "success";
}
}
