package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainPageController {

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
    List<String> incomes = Arrays.asList("Salary", "Bonus", "TravelRefund", "ShopRefund","Money transfer R", "Other");
    model.addAttribute("income_mod_attribute", new IncomeModelWeb());
    model.addAttribute("incomes", incomes);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "income";
  }

  @GetMapping("/entertainment")
  public String getEntertainmentPage(Model model) {
    List<String> entList = Arrays.asList("Travel", "Cinema", "Vacation", "Relax","Restaurant","Homefest","Other");
    model.addAttribute("entertainment_mod_attribute", new EntertainmentModelWeb());
    model.addAttribute("entList", entList);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "entertainment";
  }

  @GetMapping("/groceries")
  public String getGroceriesPage(Model model) {
    List<String> purchesList = Arrays.asList("Daily","Weekly","Weekend","Fest","Clothes","Other");
    model.addAttribute("groceries_mod_attribute", new GroceriesModelWeb());
    model.addAttribute("purchesList", purchesList);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "groceries";
  }

  @GetMapping("/health")
  public String getHealthPage(Model model) {
    List<String> healthTypeList = Arrays.asList("Dentist","Regular Medical check","Special doctor","Swimming pool","SPA","Nails","Haircut","Other");
    model.addAttribute("health_mod_attribute", new HealthModelWeb());
    model.addAttribute("healthTypeList", healthTypeList);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "health";
  }

  @GetMapping("/renthousing")
  public String getRentHousingPage(Model model) {
    List<String> housingTypes = Arrays.asList("Rent","Mortage","Money transfer S","A-kass","Electricity","HouseEquipments","Renovation","Other");
    model.addAttribute("rent_housing_mod_attribute", new RentHousingModelWeb());
    model.addAttribute("housingTypes", housingTypes);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "renthousing";
  }

  @GetMapping("/telecom")
  public String getTelecomPage(Model model) {
    List<String> telecomOperations = Arrays.asList("Mobile","Internet","Roaming bundles","Others");
    model.addAttribute("telecom_mod_attribute", new TelecomModelWeb());
    model.addAttribute("telecomOperations", telecomOperations);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "telecom";
  }

  @GetMapping("/travel")
  public String getTravelPage(Model model) {
    List<String> travelActivities = Arrays.asList("Tickets","Hotel","FoodInTrip","TravelEntertainment","Public transport","Others");
    model.addAttribute("travel_mod_attribute", new TravelModelWeb());
    model.addAttribute("travelActivities", travelActivities);
    model.addAttribute("currencyNames", currencyNames);
    model.addAttribute("transactionTypes", transactionTypes);
    return "travel";
  }

  @GetMapping("/additional")
  public String getAdditionalFunctionsPage(Model model) {
    return "additional";
  }
}
