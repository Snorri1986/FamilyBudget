package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    model.addAttribute("card_mod_attribute", new DefaultPaymentCardModel());
    return "new_card";
  }

  @PostMapping("/registerNewCard")
  public String submitNewCardForm(@ModelAttribute("card_mod_attribute") DefaultPaymentCardModel newCardForm) {
    DefaultPaymentCardModel defaultPaymentCardModel = new DefaultPaymentCardModel();
    defaultPaymentCardModel.setCardNumber(newCardForm.getCardNumber());
    // logging
    System.out.println("New card number to DB" + defaultPaymentCardModel.toString());
    dbService.insertNewDefaultPaymentCard(defaultPaymentCardModel);
    return "s_card";
  }

  @GetMapping("/travel_report")
  public String getTravelReport(Model model) {
    model.addAttribute("travel_request_mod", new TravelReportRequestModel());
    return "travel_report";
  }
}
