package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  @GetMapping("/cardBalance")
  public String showCardBalance(@NonNull Model model) {
    try {
      int cardBalance = webFormsController.getCardBalanceFromDB();
      model.addAttribute("cardBalance", cardBalance);
    } catch (Exception e) {
      System.err.println("Error retrieving card balance: " + e.getMessage());
      model.addAttribute("error", "Unable to load card balance. Please try again later.");
      model.addAttribute("cardBalance", 0);
    }
    return "card_balance";
  }

  @GetMapping("/showLocation")
  public String showLocation(Model model) {
    LocationModel location = dbService.getLocation();
    model.addAttribute("location", location);
    return "show_location";
  }

  @GetMapping("/payment_card")
  public String setNewPaymentCard(Model model) {
    model.addAttribute("card_mod_attribute", new DefaultPaymentCardModel());
    return "new_card";
  }

  @GetMapping("/set_location")
  public String setNewLocation(Model model) {
    model.addAttribute("location_attribute", new LocationModel());
    return "location";
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

  @PostMapping("/registerNewLocation")
  public String submitNewLocation(@ModelAttribute("location_attribute") LocationModel locationModel) {
    LocationModel locationModelSendToDB = new LocationModel();
    locationModelSendToDB.setCity(locationModel.getCity());
    locationModelSendToDB.setCountry(locationModel.getCountry());
    locationModelSendToDB.setVat(locationModel.getVat());
    // logging
    System.out.println("New location has been sent to DB" + locationModelSendToDB.toString());
    dbService.insertNewLocation(locationModelSendToDB);
    return "s_location";
  }

  @GetMapping("/travel_report")
  public String getTravelReport(Model model) {
    model.addAttribute("travel_request_mod", new TravelReportRequestModel());
    return "travel_report";
  }

  @PostMapping("/getTravelReport")
  public String getTravelExpense(@ModelAttribute("travel_request_mod") TravelReportRequestModel travelReportRequestModel,Model model) {
    List<TravelReportResponseModel> reportList =
            dbService.getTravelExpenseReportDB(travelReportRequestModel);
    int totalUsdAmount =
            reportList.stream()
                    .filter(r -> r.getCurrency() == 4) // USD
                    .mapToInt(TravelReportResponseModel::getAmount)
                    .sum();

    int totalEurAmount =
            reportList.stream()
                    .filter(r -> r.getCurrency() == 1) // EUR
                    .mapToInt(TravelReportResponseModel::getAmount)
                    .sum();

    int totalDkkAmount =
            reportList.stream()
                    .filter(r -> r.getCurrency() == 3) // DKK
                    .mapToInt(TravelReportResponseModel::getAmount)
                    .sum();

    int totalUahAmount =
            reportList.stream()
                    .filter(r -> r.getCurrency() == 2) // UAH
                    .mapToInt(TravelReportResponseModel::getAmount)
                    .sum();
    model.addAttribute("reportList", reportList);
    model.addAttribute("SumUSD", totalUsdAmount);
    model.addAttribute("SumEUR", totalEurAmount);
    model.addAttribute("SumDKK", totalDkkAmount);
    model.addAttribute("SumUAH", totalUahAmount);
    return "travel_report";
  }

  /*@GetMapping("/expense_report")
  public String getExpenseReport() {
    return "expense_report";
  }*/

   @GetMapping("/expense_report")
   public String getExpenseReport(Model model) {
     // Currency IDs: EUR = 1, UAH = 2, DKK = 3, USD = 4
     Map<String, Object> dailyExpense = new HashMap<>();
     dailyExpense.put("EUR", dbService.getDailyExpenseReportByCurrency(1));
     dailyExpense.put("UAH", dbService.getDailyExpenseReportByCurrency(2));
     dailyExpense.put("DKK", dbService.getDailyExpenseReportByCurrency(3));
     dailyExpense.put("USD", dbService.getDailyExpenseReportByCurrency(4));

     Map<String, Object> monthlyExpense = new HashMap<>();
     monthlyExpense.put("EUR", dbService.getMonthlyExpenseReportByCurrency(1));
     monthlyExpense.put("UAH", dbService.getMonthlyExpenseReportByCurrency(2));
     monthlyExpense.put("DKK", dbService.getMonthlyExpenseReportByCurrency(3));
     monthlyExpense.put("USD", dbService.getMonthlyExpenseReportByCurrency(4));

     Map<String, Object> yearlyExpense = new HashMap<>();
     yearlyExpense.put("EUR", dbService.getAnnualExpenseReportByCurrency(1));
     yearlyExpense.put("UAH", dbService.getAnnualExpenseReportByCurrency(2));
     yearlyExpense.put("DKK", dbService.getAnnualExpenseReportByCurrency(3));
     yearlyExpense.put("USD", dbService.getAnnualExpenseReportByCurrency(4));

     model.addAttribute("dailyExpense", dailyExpense);
     model.addAttribute("monthlyExpense", monthlyExpense);
     model.addAttribute("yearlyExpense", yearlyExpense);
     return "expense_report";
   }
}
