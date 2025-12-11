package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.AtmModelWeb;
import org.snorri1986.familybud.models.DefaultPaymentCardModel;
import org.snorri1986.familybud.models.LastTenAtmOperationsModel;
import org.snorri1986.familybud.models.UserModel;
import org.snorri1986.familybud.service.DBService;
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

  @Autowired
  DBService dbService;

  @GetMapping("/atmCash")
  public String getLastTenAtmCashOperations(Model model) {
    List<String> opList = Arrays.asList("Income", "Expenses");
    List<LastTenAtmOperationsModel> lastTenAtmOperationsModel = dbService.getLastTenAtmOperations();
    model.addAttribute("atm_mod_attribute", new AtmModelWeb());
    model.addAttribute("opList", opList);
    model.addAttribute("lastTenAtmOperations", lastTenAtmOperationsModel);
    return "atm_cash";
  }

  @GetMapping("/cashBalance")
  public String showCashBalance(Model model) {
    int cashBalance = webFormsController.getCashBalanceFromDB();
    model.addAttribute("cashBalance", cashBalance);
    return "cash_balance";
  }

  @GetMapping("/payment_card")
  public String setNewPaymentCard(Model model) {
    model.addAttribute("login_mod_attribute", new DefaultPaymentCardModel());
    return "new_card";
  }
}
