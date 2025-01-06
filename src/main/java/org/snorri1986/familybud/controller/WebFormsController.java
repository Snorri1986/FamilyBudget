package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.Utils;
import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebFormsController {

  @Autowired
  DBService dbService;

  @PostMapping("/registerIncome")
  public String submitIncomeForm(@ModelAttribute("income_mod_attribute") IncomeModelWeb income) {
    System.out.println("New income" + income.toString());
    IncomeModelDB incomeModelDB = new IncomeModelDB();

    // convert values from web form
    switch (income.getIncomeType()) {
      case "Salary": incomeModelDB.setIncomeType(14); break;
      case "Bonus": incomeModelDB.setIncomeType(13); break;
      case "TravelRefund":  incomeModelDB.setIncomeType(15); break;
      case "ShopRefund":  incomeModelDB.setIncomeType(16); break;
      case "Money transfer R":  incomeModelDB.setIncomeType(41); break;
      case "Other":  incomeModelDB.setIncomeType(17); break;
    }

    incomeModelDB.setAmount(income.getAmount());
    incomeModelDB.setCurrency(Utils.currencyConvert(income.getCurrency()));

    incomeModelDB.setTransactionDate(income.getTransactionDate());
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

    switch (entModel.getEventType()) {
      case "Travel": entertainmentModelDB.setEventType(8); break;
      case "Cinema": entertainmentModelDB.setEventType(13); break;
      case "Vacation": entertainmentModelDB.setEventType(14); break;
      case "Relax": entertainmentModelDB.setEventType(15); break;
      case "Restaurant": entertainmentModelDB.setEventType(38); break;
      case "Homefest": entertainmentModelDB.setEventType(16); break;
      case "Other": entertainmentModelDB.setEventType(17); break;
    }

    entertainmentModelDB.setAmount(entModel.getAmount());
    entertainmentModelDB.setCurrency(Utils.currencyConvert(entModel.getCurrency()));

    entertainmentModelDB.setTransactionDate(entModel.getTransactionDate());
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

    switch (grocModel.getPurchesType()) {
      case "Daily": groceriesModelDB.setPurchesType(18); break;
      case "Weekly": groceriesModelDB.setPurchesType(19); break;
      case "Weekend": groceriesModelDB.setPurchesType(20); break;
      case "Fest": groceriesModelDB.setPurchesType(21); break;
      case "Other": groceriesModelDB.setPurchesType(22); break;
    }

    groceriesModelDB.setAmount(grocModel.getAmount());
    groceriesModelDB.setCurrency(Utils.currencyConvert(grocModel.getCurrency()));
    groceriesModelDB.setTransactionDate(grocModel.getTransactionDate());
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

    switch(healthModel.getHealthOperType()) {
      case "Dentist": healthModelDB.setHealthOperType(23); break;
      case "Regular Medical check": healthModelDB.setHealthOperType(24); break;
      case "Special doctor": healthModelDB.setHealthOperType(25); break;
      case "Swimming pool": healthModelDB.setHealthOperType(26); break;
      case "SPA": healthModelDB.setHealthOperType(27); break;
      case "Nails": healthModelDB.setHealthOperType(28); break;
      case "Other": healthModelDB.setHealthOperType(22); break;
    }

    healthModelDB.setAmount(healthModel.getAmount());
    healthModelDB.setCurrency(Utils.currencyConvert(healthModel.getCurrency()));
    healthModelDB.setTransactionDate(healthModel.getTransactionDate());
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

    switch (rentHousingModel.getHousingType()) {
      case "Rent": rentHousingModelDB.setHousingType(2); break;
      case "Mortage": rentHousingModelDB.setHousingType(37); break;
      case "Money transfer S": rentHousingModelDB.setHousingType(40); break;
      case "A-kass": rentHousingModelDB.setHousingType(39); break;
      case "Electricity": rentHousingModelDB.setHousingType(29); break;
      case "HouseEquipments": rentHousingModelDB.setHousingType(30); break;
      case "Renovation": rentHousingModelDB.setHousingType(31); break;
      case "Other": rentHousingModelDB.setHousingType(22); break;
    }

    rentHousingModelDB.setAmount(rentHousingModel.getAmount());
    rentHousingModelDB.setCurrency(Utils.currencyConvert(rentHousingModel.getCurrency()));
    rentHousingModelDB.setTransactionDate(rentHousingModel.getTransactionDate());
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

    switch (telecomModel.getTelecomType()) {
      case "Mobile": telecomModelDB.setTelecomType(5); break;
      case "Internet": telecomModelDB.setTelecomType(6); break;
      case "Roaming bundles": telecomModelDB.setTelecomType(32); break;
      case "Others": telecomModelDB.setTelecomType(12); break;
    }

    telecomModelDB.setAmount(telecomModel.getAmount());
    telecomModelDB.setCurrency(Utils.currencyConvert(telecomModel.getCurrency()));
    telecomModelDB.setTransactionDate(telecomModel.getTransactionDate());
    telecomModelDB.setCardNum(telecomModel.getCardNum());
    telecomModelDB.setOperDescription(telecomModel.getOperDescription());

    dbService.insertNewTelecom(telecomModelDB);
    return "s_telecom";
  }

  @PostMapping("/registerTravel")
  public String submitTravelForm(@ModelAttribute("travel_mod_attribute") TravelModelWeb travelModel) {
    System.out.println("Travel purchase Registered: " + travelModel.toString());
    TravelModelDB travelModelDB = new TravelModelDB();

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
    travelModelDB.setCardNum(travelModel.getCardNum());
    travelModelDB.setDestination(travelModel.getDestination());
    travelModelDB.setOperDescription(travelModel.getOperDescription());

    dbService.insertNewTravel(travelModelDB);
    return "s_travel";
  }
}
