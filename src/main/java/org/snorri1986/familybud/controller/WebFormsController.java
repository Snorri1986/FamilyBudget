package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.IncomeModel;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebFormsController {

  @PostMapping("/registerIncome")
  public String submitIncomeForm(@ModelAttribute("income_mod_attribute") IncomeModel income) {
    // Temporary code
    System.out.println("Income Registered: " + income.getOperDescription());
    //TODO: add this page
    return "register_success";
  }
}
