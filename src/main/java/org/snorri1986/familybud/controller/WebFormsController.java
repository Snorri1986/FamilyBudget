package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.Utils;
import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class WebFormsController {

  @Autowired
  DBService dbService;

  private static final Map<String, Integer> INCOME_TYPES = Map.of(
          "Salary", 14,
          "Bonus", 13,
          "TravelRefund", 15,
          "ShopRefund", 16,
          "Money transfer R", 41,
          "HumanRefund", 18,
          "Other", 17
  );

  private static final Map<String, Integer> ENTERTAINMENT_TYPES = Map.of(
          "Travel", 8,
          "Cinema", 13,
          "Vacation", 14,
          "Relax", 15,
          "Restaurant", 38,
          "Homefest", 16,
          "Other", 17
  );

  private static final Map<String, Integer> GROCERY_TYPES = Map.of (
         "Daily", 18,
         "Weekly", 19,
         "Weekend", 20,
         "Fest", 21,
         "Clothes", 43,
         "Lunch at work", 44,
         "Other", 22
  );

  private static final Map<String, Integer> HEALTH_TYPES = Map.of (
          "Dentist",23,
          "Regular Medical check", 24,
          "Special doctor", 25,
          "Swimming pool", 26,
          "SPA", 27,
          "Nails", 28,
          "Haircut", 42,
          "Gym", 45,
          "Other", 22
  );

  private static final Map<String, Integer> RENT_HOUSING_TYPES = Map.of (
          "Rent", 2,
          "Mortgage", 37,
          "Money transfer S", 40,
          "A-kass", 39,
          "Electricity", 29,
          "House equipment", 30,
          "Renovation", 31,
          "Other", 22
  );

  @PostMapping("/toMain")
  public String goToMain(@ModelAttribute("login_mod_attribute") UserModel uModel) {
    int loginValidationResult = dbService.checkLogin(uModel);
    if (loginValidationResult == 1) return "main";
    else return "wrong_auth";
  }

  @PostMapping("/registerIncome")
  public String submitIncomeForm(@ModelAttribute("income_mod_attribute") IncomeModelWeb income) {
    System.out.println("New income" + income.toString());
    IncomeModelDB incomeModelDB = new IncomeModelDB();

    // input gathering
    Integer incomeTypeId = INCOME_TYPES.get(income.getIncomeType());

    // input validation
    if (incomeTypeId == null) {
      throw new IllegalArgumentException("Unknown income type: " + income.getIncomeType());
    }

    incomeModelDB.setIncomeType(incomeTypeId);

    incomeModelDB.setAmount(income.getAmount());
    incomeModelDB.setCurrency(Utils.currencyConvert(income.getCurrency()));

    incomeModelDB.setTransactionDate(income.getTransactionDate());
    incomeModelDB.setTransactionType(income.getTransactionType());
    incomeModelDB.setCardNum(income.getCardNum());
    incomeModelDB.setOperDescription(income.getOperDescription());

    System.out.println("New income to DB" + incomeModelDB.toString());

    dbService.insertNewIncome(incomeModelDB);
    return "s_income";
  }

  @PostMapping("/registerEntertainment")
  public String submitEntertainmentForm(@ModelAttribute("entertainment_mod_attribute") EntertainmentModelWeb entModel) {
    System.out.println("Entertainment Registered: " + entModel.toString());
    EntertainmentModelDB entertainmentModelDB = new EntertainmentModelDB();

    Integer entertainmentTypeId = ENTERTAINMENT_TYPES.get(entModel.getEventType());

    if (entertainmentTypeId == null) {
      throw new IllegalArgumentException("Unknown entertainment type: " + entModel.getEventType());
    }

    entertainmentModelDB.setEventType(entertainmentTypeId);
    entertainmentModelDB.setAmount(entModel.getAmount());
    entertainmentModelDB.setCurrency(Utils.currencyConvert(entModel.getCurrency()));
    entertainmentModelDB.setTransactionDate(entModel.getTransactionDate());
    entertainmentModelDB.setTransactionType(entModel.getTransactionType());
    entertainmentModelDB.setCardNum(entModel.getCardNum());
    entertainmentModelDB.setOperDescription(entModel.getOperDescription());

    System.out.println("New entertainment to DB: " + entertainmentModelDB.toString());

    dbService.insertNewEntertainment(entertainmentModelDB);
    return "s_entertainment";
  }

  @PostMapping("/registerGroceries")
  public String submitGroceriesForm(@ModelAttribute("groceries_mod_attribute") GroceriesModelWeb grocModel) {
    System.out.println("Groceries purchase Registered: " + grocModel.toString());
    GroceriesModelDB groceriesModelDB = new GroceriesModelDB();

    Integer groceriesTypeId = GROCERY_TYPES.get(grocModel.getPurchaseType());

    if (groceriesTypeId == null) {
      throw new IllegalArgumentException("Unknown groceries type: " + grocModel.getPurchaseType());
    }

    groceriesModelDB.setPurchaseType(groceriesTypeId);
    groceriesModelDB.setAmount(grocModel.getAmount());
    groceriesModelDB.setCurrency(Utils.currencyConvert(grocModel.getCurrency()));
    groceriesModelDB.setTransactionDate(grocModel.getTransactionDate());
    groceriesModelDB.setTransactionType(grocModel.getTransactionType());
    groceriesModelDB.setCardNum(grocModel.getCardNum());
    groceriesModelDB.setOperDescription(grocModel.getOperDescription());

    System.out.println("New groceries to DB: " + groceriesModelDB.toString());

    dbService.insertNewGroceries(groceriesModelDB);
    return "s_groceries";
  }

  @PostMapping("/registerHealth")
  public String submitHealthForm(@ModelAttribute("health_mod_attribute") HealthModelWeb healthModel) {
    System.out.println("Health purchase Registered: " + healthModel.toString());
    HealthModelDB healthModelDB = new HealthModelDB();

    Integer healthTypeId = HEALTH_TYPES.get(healthModel.getHealthOperationType());

    if (healthTypeId == null) {
      throw new IllegalArgumentException("Unknown health type: " + healthModel.getHealthOperationType());
    }

    healthModelDB.setHealthOperationType(healthTypeId);
    healthModelDB.setAmount(healthModel.getAmount());
    healthModelDB.setCurrency(Utils.currencyConvert(healthModel.getCurrency()));
    healthModelDB.setTransactionDate(healthModel.getTransactionDate());
    healthModelDB.setTransactionType(healthModel.getTransactionType());
    healthModelDB.setCardNum(healthModel.getCardNum());
    healthModelDB.setOperDescription(healthModel.getOperDescription());

    System.out.println("New health to DB: " + healthModelDB.toString());

    dbService.insertNewHealth(healthModelDB);
    return "s_health";
  }

  @PostMapping("/registerHousing")
  public String submitHousingForm(@ModelAttribute("rent_housing_mod_attribute") RentHousingModelWeb rentHousingModel) {
    System.out.println("RentHousing purchase Registered: " + rentHousingModel.toString());
    RentHousingModelDB rentHousingModelDB = new RentHousingModelDB();

    Integer rentHousingTypeId = RENT_HOUSING_TYPES.get(rentHousingModel.getHousingType());

    if (rentHousingTypeId == null) {
      throw new IllegalArgumentException("Unknown rent housing type: " + rentHousingModel.getHousingType());
    }

    rentHousingModelDB.setHousingType(rentHousingTypeId);
    rentHousingModelDB.setAmount(rentHousingModel.getAmount());
    rentHousingModelDB.setCurrency(Utils.currencyConvert(rentHousingModel.getCurrency()));
    rentHousingModelDB.setTransactionDate(rentHousingModel.getTransactionDate());
    rentHousingModelDB.setTransactionType(rentHousingModel.getTransactionType());
    rentHousingModelDB.setCardNum(rentHousingModel.getCardNum());
    rentHousingModelDB.setOperDescription(rentHousingModel.getOperDescription());

    System.out.println("New RentHousing DB model: " + rentHousingModelDB.toString());

    dbService.insertNewRentHousing(rentHousingModelDB);
    return "s_renthousing";
  }


  @PostMapping("/registerTelecom")
  public String submitTelecomForm(@ModelAttribute("telecom_mod_attribute") TelecomModelWeb telecomModel) {
    System.out.println("Telecom purchase Registered: " + telecomModel.toString());
    TelecomModelDB telecomModelDB = new TelecomModelDB();

    //TODO: replace via ENUM
    switch (telecomModel.getTelecomType()) {
      case "Mobile": telecomModelDB.setTelecomType(5); break;
      case "Internet": telecomModelDB.setTelecomType(6); break;
      case "Roaming bundles": telecomModelDB.setTelecomType(32); break;
      case "Others": telecomModelDB.setTelecomType(12); break;
    }

    telecomModelDB.setAmount(telecomModel.getAmount());
    telecomModelDB.setCurrency(Utils.currencyConvert(telecomModel.getCurrency()));
    telecomModelDB.setTransactionDate(telecomModel.getTransactionDate());
    telecomModelDB.setTransactionType(telecomModel.getTransactionType());
    telecomModelDB.setCardNum(telecomModel.getCardNum());
    telecomModelDB.setOperDescription(telecomModel.getOperDescription());

    System.out.println("Telecom model DB: " + telecomModelDB.toString());

    dbService.insertNewTelecom(telecomModelDB);
    return "s_telecom";
  }

  @PostMapping("/registerTravel")
  public String submitTravelForm(@ModelAttribute("travel_mod_attribute") TravelModelWeb travelModel) {
    System.out.println("Travel purchase Registered: " + travelModel.toString());
    TravelModelDB travelModelDB = new TravelModelDB();

    //TODO: replace via ENUM
    switch(travelModel.getTravelType()) {
      case "Tickets": travelModelDB.setTravelType(33); break;
      case "Hotel": travelModelDB.setTravelType(34); break;
      case "FoodInTrip": travelModelDB.setTravelType(35); break;
      case "TravelEntertainment": travelModelDB.setTravelType(36); break;
      case "Public transport": travelModelDB.setTravelType(7); break;
      case "Others": travelModelDB.setTravelType(12); break;
    }

    travelModelDB.setAmount(travelModel.getAmount());
    travelModelDB.setCurrency(Utils.currencyConvert(travelModel.getCurrency()));
    travelModelDB.setTransactionDate(travelModel.getTransactionDate());
    travelModelDB.setTransactionType(travelModel.getTransactionType());
    travelModelDB.setCardNum(travelModel.getCardNum());
    travelModelDB.setDestination(travelModel.getDestination());
    travelModelDB.setOperDescription(travelModel.getOperDescription());

    System.out.println("New Travel DB: " + travelModelDB.toString());

    dbService.insertNewTravel(travelModelDB);
    return "s_travel";
  }

  @PostMapping("/registerAtm")
  public String submitNewAtmCash(@ModelAttribute("atm_mod_attribute") AtmModelWeb atmModelWeb) {
    System.out.println("ATM cash Registered: " + atmModelWeb.toString());
    dbService.insertNewAtmCash(atmModelWeb);
    return "s_atm_cash";
  }

  public int getCashBalanceFromDB() {
    return dbService.getCashBalance();
  }
}
