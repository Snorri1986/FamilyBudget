package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.AtmModelWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdditionalFuncPageController {

  @Autowired
  WebFormsController webFormsController;

  @GetMapping("/atmCash")
  public String getAtmCashPage(Model model) {
    List<String> opList = Arrays.asList("Income", "Expenses");
    model.addAttribute("atm_mod_attribute", new AtmModelWeb());
    model.addAttribute("opList", opList);
    return "atm_cash";
  }

  @GetMapping("/cashBalance")
  public String showCashBalance(Model model) {
    int cashBalance = webFormsController.getCashBalanceFromDB();
    model.addAttribute("cashBalance", cashBalance);
    return "cash_balance";
  }
}
