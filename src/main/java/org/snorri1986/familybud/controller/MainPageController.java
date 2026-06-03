package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class MainPageController {

  @Autowired
  DBService dbService;

  private static final List<String> CURRENCY_NAMES = Collections.unmodifiableList(
          Arrays.asList("DKK", "EUR", "USD", "UAH", "Other"));

  private static final List<String> TRANSACTION_TYPES = Collections.unmodifiableList(
          Arrays.asList("Card", "Cash"));

  private static final List<String> INCOME_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Salary", "Bonus", "TravelRefund", "ShopRefund","Money transfer R","HumanRefund","Other"));

  private static final List<String> ENTERTAINMENT_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Travel", "Cinema", "Vacation", "Relax","Restaurant","Homefest","Other"));

  private static final List<String> GROCERIES_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Daily","Weekly","Weekend","Fest","Clothes","Lunch at work","Other"));

  private static final List<String> HEALTH_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Dentist","Regular Medical check","Special doctor","Swimming pool","SPA","Nails","Haircut","Gym","Other"));

  private static final List<String> RENT_HOUSING_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Rent","Mortgage","Money transfer S","A-kass","Electricity","House equipment","Renovation","Other"));

  private static final List<String> TELECOM_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Mobile","Internet","Roaming bundles","Other"));

  private static final List<String> TRAVEL_TYPE_LIST = Collections.unmodifiableList(
          Arrays.asList("Tickets","Hotel","Food in trip","Travel entertainment","Public transport","Other"));

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    model.addAttribute("login_mod_attribute", new UserModel());
    return "login";
  }

  @GetMapping("/main")
  public String getMainPage(Model model) {
    return "main";
  }

  @GetMapping("/income")
  public String getIncomePage(Model model) {
    List<LastTenIncomesModel> lastTenIncomesCard = Optional.ofNullable(dbService.getLastTenIncomesCard()).orElse(Collections.emptyList());
    List<LastTenIncomesModel> lastTenIncomesCash = Optional.ofNullable(dbService.getLastTenIncomesCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("income_mod_attribute", new IncomeModelWeb(defaultPaymentCard));
    model.addAttribute("incomes", INCOME_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("incomesLastTenCard", lastTenIncomesCard);
    model.addAttribute("incomesLastTenCash", lastTenIncomesCash);
    return "income";
  }

  @GetMapping("/entertainment")
  public String getEntertainmentPage(Model model) {
    List<LastTenEntertainmentModel> lastTenEntertainmentCard = Optional.ofNullable(dbService.getLastTenEntertainmentOperationsCard()).orElse(Collections.emptyList());
    List<LastTenEntertainmentModel> lastTenEntertainmentCash = Optional.ofNullable(dbService.getLastTenEntertainmentOperationsCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("entertainment_mod_attribute", new EntertainmentModelWeb(defaultPaymentCard));
    model.addAttribute("entList", ENTERTAINMENT_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("entertainmentLastTenCard", lastTenEntertainmentCard);
    model.addAttribute("entertainmentLastTenCash", lastTenEntertainmentCash);
    return "entertainment";
  }

  @GetMapping("/groceries")
  public String getGroceriesPage(Model model) {
    List<LastTenGroceriesOperModel> lastTenGroceriesCard = Optional.ofNullable(dbService.getLastTenGroceriesOperationsCard()).orElse(Collections.emptyList());
    List<LastTenGroceriesOperModel> lastTenGroceriesCash = Optional.ofNullable(dbService.getLastTenGroceriesOperationsCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("groceries_mod_attribute", new GroceriesModelWeb(defaultPaymentCard));
    model.addAttribute("purchesList", GROCERIES_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("groceriesLastTenCard", lastTenGroceriesCard);
    model.addAttribute("groceriesLastTenCash", lastTenGroceriesCash);
    return "groceries";
  }

  @GetMapping("/health-care")
  public String getHealthPage(Model model) {
    List<LastTenHealthOperationsModel> lastTenHealthCardOperations =
            Optional.ofNullable(dbService.getLastTenHealthOperationsCard()).orElse(Collections.emptyList());
    List<LastTenHealthOperationsModel> lastTenHealthCashOperations = Optional.ofNullable(dbService.getLastTenHealthOperationsCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("health_mod_attribute", new HealthModelWeb(defaultPaymentCard));
    model.addAttribute("healthTypeList",HEALTH_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("healthLastTenCardOperations", lastTenHealthCardOperations);
    model.addAttribute("healthLastTenCashOperations", lastTenHealthCashOperations);
    return "health";
  }

  @GetMapping("/renthousing")
  public String getRentHousingPage(Model model) {
    List<LastTenHousingRentOperationsModel> lastTenRentHousingCard = Optional.ofNullable(dbService.getLastTenHousingRentOperationsCard()).orElse(Collections.emptyList());
    List<LastTenHousingRentOperationsModel> lastTenRentHousingCash = Optional.ofNullable(dbService.getLastTenHousingRentOperationsCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("rent_housing_mod_attribute", new RentHousingModelWeb(defaultPaymentCard));
    model.addAttribute("housingTypes", RENT_HOUSING_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("rentHousingLastTenCard", lastTenRentHousingCard);
    model.addAttribute("rentHousingLastTenCash", lastTenRentHousingCash);
    return "renthousing";
  }

  @GetMapping("/telecom")
  public String getTelecomPage(Model model) {
    List<LastTenTelecomOperationsModel> lastTenTelecomCard = Optional.ofNullable(dbService.getLastTenTelecomOperationsCard()).orElse(Collections.emptyList());
    List<LastTenTelecomOperationsModel> lastTenTelecomCash = Optional.ofNullable(dbService.getLastTenTelecomOperationsCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("telecom_mod_attribute", new TelecomModelWeb(defaultPaymentCard));
    model.addAttribute("telecomOperations",TELECOM_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("telecomLastTenCard", lastTenTelecomCard);
    model.addAttribute("telecomLastTenCash", lastTenTelecomCash);
    return "telecom";
  }

  @GetMapping("/travel")
  public String getTravelPage(Model model) {
    List<LastTenTravelOperationsModel> lastTenTravelCard = Optional.ofNullable(dbService.getLastTenTravelOperationsCard()).orElse(Collections.emptyList());
    List<LastTenTravelOperationsModel> lastTenTravelCash = Optional.ofNullable(dbService.getLastTenTravelOperationsCash()).orElse(Collections.emptyList());
    int defaultPaymentCard = Optional.ofNullable(dbService.getPaymentCardDefault()).orElse(0);
    model.addAttribute("travel_mod_attribute", new TravelModelWeb(defaultPaymentCard));
    model.addAttribute("travelActivities",TRAVEL_TYPE_LIST);
    model.addAttribute("currencyNames", CURRENCY_NAMES);
    model.addAttribute("transactionTypes", TRANSACTION_TYPES);
    model.addAttribute("travelLastTenCard", lastTenTravelCard);
    model.addAttribute("travelLastTenCash", lastTenTravelCash);
    return "travel";
  }

  @GetMapping("/additional")
  public String getAdditionalFunctionsPage(Model model) {
    return "additional";
  }

  @GetMapping("/information")
  public String getInfo(Model model) {
    model.addAttribute("theApplication","Family Budget");
    model.addAttribute("theVersion","2.1.0");
    model.addAttribute("theSources","https://github.com/Snorri1986/FamilyBudget/releases");
    return "information";
  }
}
