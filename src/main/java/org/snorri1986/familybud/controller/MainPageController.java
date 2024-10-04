package org.snorri1986.familybud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

  @GetMapping("/main")
  public String getMainPage(Model model) {
    //TODO: temp attribute.
    model.addAttribute("theDate",java.time.LocalDateTime.now());
    return "main";
  }

  @GetMapping("/income")
  public String getIncomePage() {
    return "income";
  }

  @GetMapping("/entertainment")
  public String getEntertainmentPage() {
    return "entertainment";
  }
}
