package org.snorri1986.familybud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdditionalFuncPageController {

  @GetMapping("/atmCash")
  public String getAtmCashPage(Model model) {
    return "atm_cash";
  }
}
