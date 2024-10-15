package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.IncomeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainPageController {

  @GetMapping("/main")
  public String getMainPage(Model model) {
    //TODO: temp attribute.
    model.addAttribute("theDate",java.time.LocalDateTime.now());
    return "main";
  }

  @GetMapping("/income")
  public String getIncomePage(Model model) {
    List<String> incomes = Arrays.asList("Salary", "Bonus", "WorkRefund", "ShopRefund", "Other");
    model.addAttribute("income_mod_attribute", new IncomeModel());
    model.addAttribute("incomes", incomes);
    return "income";
  }

  @GetMapping("/entertainment")
  public String getEntertainmentPage() {
    return "entertainment";
  }

  @GetMapping("/groceries")
  public String getGroceriesPage() {
    return "groceries";
  }

  @GetMapping("/health")
  public String getHealthPage() {
    return "health";
  }

  @GetMapping("/renthousing")
  public String getRentHousingPage() {
    return "renthousing";
  }

  @GetMapping("/telecom")
  public String getTelecomPage() {
    return "telecom";
  }

  @GetMapping("/travel")
  public String getTravelPage() {
    return "travel";
  }

  @GetMapping("/others")
  public String getOthersPage() {
    return "others";
  }
}
