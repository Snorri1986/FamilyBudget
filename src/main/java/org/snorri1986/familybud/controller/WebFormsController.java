package org.snorri1986.familybud.controller;

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
  public String submitIncomeForm(@ModelAttribute("income_mod_attribute") IncomeModel income) {
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

    // TODO: replace and use function method
    switch (income.getCurrency()) {
      case "DKK": incomeModelDB.setCurrency(3);
      case "EUR": incomeModelDB.setCurrency(1);
      case "UAH": incomeModelDB.setCurrency(2);
      case "USD": incomeModelDB.setCurrency(4);
    }

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

    // TODO: replace repeat code
    switch (entModel.getCurrency()) {
      case "DKK": entertainmentModelDB.setCurrency(3);
      case "EUR": entertainmentModelDB.setCurrency(1);
      case "UAH": entertainmentModelDB.setCurrency(2);
      case "USD": entertainmentModelDB.setCurrency(4);
    }

    entertainmentModelDB.setTransactionDate(entModel.getTransactionDate());
    entertainmentModelDB.setCardNum(entModel.getCardNum());
    entertainmentModelDB.setOperDescription(entModel.getOperDescription());

    dbService.insertNewEntertainment(entertainmentModelDB);
    return "s_entertainment";
  }

  @PostMapping("/registerGroceries")
  public String submitGroceriesForm(@ModelAttribute("groceries_mod_attribute") GroceriesModel grocModel) {
    // Temporary code
    System.out.println("Groceries purchase Registered: " + grocModel.getOperDescription());
    return "s_groceries";
  }

  @PostMapping("/registerHealth")
  public String submitHealthForm(@ModelAttribute("health_mod_attribute") HealthModel healthModel) {
    // Temporary code
    System.out.println("Health purchase Registered: " + healthModel.getOperDescription());
    return "s_health";
  }

  @PostMapping("/registerHousing")
  public String submitHousingForm(@ModelAttribute("rent_housing_mod_attribute") RentHousingModel rentHousingModel) {
    // Temporary code
    System.out.println("RentHousing purchase Registered: " + rentHousingModel.getOperDescription());
    return "s_renthousing";
  }

  @PostMapping("/registerTelecom")
  public String submitTelecomForm(@ModelAttribute("telecom_mod_attribute") TelecomModel telecomModel) {
    // Temporary code
    System.out.println("Telecom purchase Registered: " + telecomModel.getOperDescription());
    return "s_telecom";
  }

  @PostMapping("/registerTravel")
  public String submitTravelForm(@ModelAttribute("travel_mod_attribute") TravelModel travelModel) {
    // Temporary code
    System.out.println("Travel purchase Registered: " + travelModel.getOperDescription());
    return "s_travel";
  }
}
