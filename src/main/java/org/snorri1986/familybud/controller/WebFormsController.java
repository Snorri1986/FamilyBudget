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
      case "Salary": incomeModelDB.setIncomeType(14);
      case "Bonus": incomeModelDB.setIncomeType(13);
      case "TravelRefund":  incomeModelDB.setIncomeType(15);
      case "ShopRefund":  incomeModelDB.setIncomeType(16);
      case "Other":  incomeModelDB.setIncomeType(17);
    }

    incomeModelDB.setAmount(income.getAmount());
    incomeModelDB.setCurrency(Utils.currencyConvert(income.getCurrency()));

    incomeModelDB.setTransactionDate(income.getTransactionDate());
    incomeModelDB.setCardNum(income.getCardNum());
    incomeModelDB.setOperDescription(income.getOperDescription());

    dbService.insertNewIncome(incomeModelDB);
    return "s_income";
  }

  @PostMapping("/registerEntertainment")
  public String submitEntertainmentForm(@ModelAttribute("entertainment_mod_attribute") EntertainmentModelWeb entModel) {
    System.out.println("Entertainment Registered: " + entModel.toString());
    EntertainmentModelDB entertainmentModelDB = new EntertainmentModelDB();
    switch (entModel.getEventType()) {
      case "Travel": entertainmentModelDB.setEventType(8);
      case "Cinema": entertainmentModelDB.setEventType(13);
      case "Vacation": entertainmentModelDB.setEventType(14);
      case "Relax": entertainmentModelDB.setEventType(15);
      case "Homefest": entertainmentModelDB.setEventType(16);
      case "Other": entertainmentModelDB.setEventType(17);
    }

    entertainmentModelDB.setAmount(entModel.getAmount());
    entertainmentModelDB.setCurrency(Utils.currencyConvert(entModel.getCurrency()));

    entertainmentModelDB.setTransactionDate(entModel.getTransactionDate());
    entertainmentModelDB.setCardNum(entModel.getCardNum());
    entertainmentModelDB.setOperDescription(entModel.getOperDescription());

    dbService.insertNewEntertainment(entertainmentModelDB);
    return "s_entertainment";
  }

  @PostMapping("/registerGroceries")
  public String submitGroceriesForm(@ModelAttribute("groceries_mod_attribute") GroceriesModelWeb grocModel) {
    System.out.println("Groceries purchase Registered: " + grocModel.toString());
    GroceriesModelDB groceriesModelDB = new GroceriesModelDB();

    switch (grocModel.getPurchesType()) {
      case "Daily": groceriesModelDB.setPurchesType(18);
      case "Weekly": groceriesModelDB.setPurchesType(19);
      case "Weekend": groceriesModelDB.setPurchesType(20);
      case "Fest": groceriesModelDB.setPurchesType(21);
      case "Other": groceriesModelDB.setPurchesType(22);
    }

    groceriesModelDB.setAmount(grocModel.getAmount());
    groceriesModelDB.setCurrency(Utils.currencyConvert(grocModel.getCurrency()));
    groceriesModelDB.setTransactionDate(grocModel.getTransactionDate());
    groceriesModelDB.setCardNum(grocModel.getCardNum());
    groceriesModelDB.setOperDescription(grocModel.getOperDescription());

    dbService.insertNewGroceries(groceriesModelDB);
    return "s_groceries";
  }

  @PostMapping("/registerHealth")
  public String submitHealthForm(@ModelAttribute("health_mod_attribute") HealthModelWeb healthModel) {
    System.out.println("Health purchase Registered: " + healthModel.toString());
    HealthModelDB healthModelDB = new HealthModelDB();

    switch(healthModel.getHealthOperType()) {
      case "Dentist": healthModelDB.setHealthOperType(23);
      case "Regular Medical check": healthModelDB.setHealthOperType(24);
      case "Special doctor": healthModelDB.setHealthOperType(25);
      case "Swimming pool": healthModelDB.setHealthOperType(26);
      case "SPA": healthModelDB.setHealthOperType(27);
      case "Nails": healthModelDB.setHealthOperType(28);
      case "Other": healthModelDB.setHealthOperType(22);
    }

    healthModelDB.setAmount(healthModel.getAmount());
    healthModelDB.setCurrency(Utils.currencyConvert(healthModel.getCurrency()));
    healthModelDB.setTransactionDate(healthModel.getTransactionDate());
    healthModelDB.setCardNum(healthModel.getCardNum());
    healthModelDB.setOperDescription(healthModel.getOperDescription());

    dbService.insertNewHealth(healthModelDB);
    return "s_health";
  }

  @PostMapping("/registerHousing")
  public String submitHousingForm(@ModelAttribute("rent_housing_mod_attribute") RentHousingModelWeb rentHousingModel) {
    System.out.println("RentHousing purchase Registered: " + rentHousingModel.toString());
    RentHousingModelDB rentHousingModelDB = new RentHousingModelDB();

    switch (rentHousingModel.getHousingType()) {
      case "Rent": rentHousingModelDB.setHousingType(2);
      case "Electricity": rentHousingModelDB.setHousingType(29);
      case "HouseEquipments": rentHousingModelDB.setHousingType(30);
      case "Renovation": rentHousingModelDB.setHousingType(31);
      case "Other": rentHousingModelDB.setHousingType(22);
    }

    rentHousingModelDB.setAmount(rentHousingModel.getAmount());
    rentHousingModelDB.setCurrency(Utils.currencyConvert(rentHousingModel.getCurrency()));
    rentHousingModelDB.setTransactionDate(rentHousingModel.getTransactionDate());
    rentHousingModelDB.setCardNum(rentHousingModel.getCardNum());
    rentHousingModelDB.setOperDescription(rentHousingModel.getOperDescription());

    dbService.insertNewRentHousing(rentHousingModelDB);
    return "s_renthousing";
  }

  @PostMapping("/registerTelecom")
  public String submitTelecomForm(@ModelAttribute("telecom_mod_attribute") TelecomModelWeb telecomModel) {
    System.out.println("Telecom purchase Registered: " + telecomModel.toString());
    TelecomModelDB telecomModelDB = new TelecomModelDB();

    switch (telecomModel.getTelecomType()) {
      case "Mobile": telecomModelDB.setTelecomType(5);
      case "Internet": telecomModelDB.setTelecomType(6);
      case "Roaming bundles": telecomModelDB.setTelecomType(32);
      case "Others": telecomModelDB.setTelecomType(12);
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
      case "Tickets": travelModelDB.setTravelType(33);
      case "Hotel": travelModelDB.setTravelType(34);
      case "FoodInTrip": travelModelDB.setTravelType(35);
      case "TravelEntertainment": travelModelDB.setTravelType(36);
      case "Others": travelModelDB.setTravelType(12);
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
