package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainPageController {

  @Autowired
  DBService dbService;

  List<String> currencyNames = Arrays.asList("DKK", "EUR", "USD", "UAH", "Other");
  List<String> transactionTypes = Arrays.asList("Card", "Cash");

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    model.addAttribute("login_mod_attribute", new UserModel());
    return "login";
  }

  @GetMapping("/main")
  public String getMainPage(Model model) {
    model.addAttribute("theDate",java.time.LocalDateTime.now());
    return "main";
  }

  @GetMapping("/income")
  public String getIncomePage(Model model) {
    List<String> incomes = Arrays.asList("Salary", "Bonus", "TravelRefund", "ShopRefund","Money transfer R","HumanRefund","Other");
    List<LastTenIncomesModel> lastTenIncomesCard = dbService.getLastTenIncomesCard();
    List<LastTenIncomesModel> lastTenIncomesCash = dbService.getLastTenIncomesCash();
    int defaultPaymentCard = dbService.getPaymentCardDefault();
    model.addAttribute("income_mod_attribute", new IncomeModelWeb(defaultPaymentCard));
    model.addAttribute("incomes", incomes);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    model.addAttribute("incomesLastTenCard", lastTenIncomesCard);
    model.addAttribute("incomesLastTenCash", lastTenIncomesCash);
    return "income";
  }

  @GetMapping("/entertainment")
  public String getEntertainmentPage(Model model) {
    List<String> entList = Arrays.asList("Travel", "Cinema", "Vacation", "Relax","Restaurant","Homefest","Other");
    List<LastTenEntertainmentModel> lastTenEntertainmentCard = dbService.getLastTenEntertainmentOperationsCard();
    List<LastTenEntertainmentModel> lastTenEntertainmentCash = dbService.getLastTenEntertainmentOperationsCash();
    int defaultPaymentCard = dbService.getPaymentCardDefault();
    model.addAttribute("entertainment_mod_attribute", new EntertainmentModelWeb(defaultPaymentCard));
    model.addAttribute("entList", entList);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    model.addAttribute("entertainmentLastTenCard", lastTenEntertainmentCard);
    model.addAttribute("entertainmentLastTenCash", lastTenEntertainmentCash);
    return "entertainment";
  }

  @GetMapping("/groceries")
  public String getGroceriesPage(Model model) {
    List<String> purchesList = Arrays.asList("Daily","Weekly","Weekend","Fest","Clothes","Lunch at work","Other");
    List<LastTenGroceriesOperModel> lastTenGroceriesCard = dbService.getLastTenGroceriesOperationsCard();
    List<LastTenGroceriesOperModel> lastTenGroceriesCash = dbService.getLastTenGroceriesOperationsCash();
    int defaultPaymentCard = dbService.getPaymentCardDefault();
    model.addAttribute("groceries_mod_attribute", new GroceriesModelWeb(defaultPaymentCard));
    model.addAttribute("purchesList", purchesList);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    model.addAttribute("groceriesLastTenCard", lastTenGroceriesCard);
    model.addAttribute("groceriesLastTenCash", lastTenGroceriesCash);
    return "groceries";
  }

  @GetMapping("/health-care")
  public String getHealthPage(Model model) {
    List<String> healthTypeList = Arrays.asList("Dentist","Regular Medical check","Special doctor","Swimming pool","SPA","Nails","Haircut","Other");
    List<LastTenHealthOperationsModel> lastTenHealthCardOperations = dbService.getLastTenHealthOperationsCard();
    List<LastTenHealthOperationsModel> lastTenHealthCashOperations = dbService.getLastTenHealthOperationsCash();
    int defaultPaymentCard = dbService.getPaymentCardDefault();
    model.addAttribute("health_mod_attribute", new HealthModelWeb(defaultPaymentCard));
    model.addAttribute("healthTypeList", healthTypeList);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    model.addAttribute("healthLastTenCardOperations", lastTenHealthCardOperations);
    model.addAttribute("healthLastTenCashOperations", lastTenHealthCashOperations);
    return "health";
  }

  @GetMapping("/renthousing")
  public String getRentHousingPage(Model model) {
    List<String> housingTypes = Arrays.asList("Rent","Mortage","Money transfer S","A-kass","Electricity","HouseEquipments","Renovation","Other");
    List<LastTenHousingRentOperationsModel> lastTenRentHousingCard = dbService.getLastTenHousingRentOperationsCard();
    List<LastTenHousingRentOperationsModel> lastTenRentHousingCash = dbService.getLastTenHousingRentOperationsCash();
    model.addAttribute("rent_housing_mod_attribute", new RentHousingModelWeb());
    model.addAttribute("housingTypes", housingTypes);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    model.addAttribute("rentHousingLastTenCard", lastTenRentHousingCard);
    model.addAttribute("rentHousingLastTenCash", lastTenRentHousingCash);
    return "renthousing";
  }

  @GetMapping("/telecom")
  public String getTelecomPage(Model model) {
    List<String> telecomOperations = Arrays.asList("Mobile","Internet","Roaming bundles","Others");
    List<LastTenTelecomOperationsModel> lastTenTelecomCard = dbService.getLastTenTelecomOperationsCard();
    List<LastTenTelecomOperationsModel> lastTenTelecomCash = dbService.getLastTenTelecomOperationsCash();
    model.addAttribute("telecom_mod_attribute", new TelecomModelWeb());
    model.addAttribute("telecomOperations", telecomOperations);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    model.addAttribute("telecomLastTenCard", lastTenTelecomCard);
    model.addAttribute("telecomLastTenCash", lastTenTelecomCash);
    return "telecom";
  }

  @GetMapping("/travel")
  public String getTravelPage(Model model) {
    List<String> travelActivities = Arrays.asList("Tickets","Hotel","FoodInTrip","TravelEntertainment","Public transport","Others");
    List<LastTenTravelOperationsModel> lastTenTravelCard = dbService.getLastTenTravelOperationsCard();
    List<LastTenTravelOperationsModel> lastTenTravelCash = dbService.getLastTenTravelOperationsCash();
    model.addAttribute("travel_mod_attribute", new TravelModelWeb());
    model.addAttribute("travelActivities", travelActivities);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
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
    model.addAttribute("theVersion","1.5.2");
    model.addAttribute("theSources","https://github.com/Snorri1986/FamilyBudget/releases");
    return "information";
  }
}
