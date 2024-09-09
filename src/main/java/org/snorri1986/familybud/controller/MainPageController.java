package org.snorri1986.familybud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

  @GetMapping("/main")
  public String getMainPage(Model model) {
    //TODO: temp attribute.
    model.addAttribute("the Date",java.time.LocalDateTime.now());
    return "main";
  }
}
