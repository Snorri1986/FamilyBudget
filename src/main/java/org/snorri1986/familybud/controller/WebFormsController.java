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
    //TODO:
    // New incomeIncomeModel{incomeType='Salary', amount=1000, currency='DKK', transactionDate=Wed Nov 13 12:00:00 CET 2024, cardNum=1234, operDescription='Go to DB'}
    // CREATE OR REPLACE FUNCTION public.i_income(income_type integer, amount_value integer, currency_value integer, oper_date date, payment_card integer, comm_value character
    IncomeModelDB incomeModelDB = new IncomeModelDB();
    // convert values from form
    switch (income.getIncomeType()) {
      case "Salary": incomeModelDB.setIncomeType(14);
      case "Bonus": incomeModelDB.setIncomeType(13);
      // TODO: rename to Travel Refund on html level
      case "WorkRefund":  incomeModelDB.setIncomeType(15);
      case "ShopRefund":  incomeModelDB.setIncomeType(16);
        // TODO: rename on DB level to others
      case "Others":  incomeModelDB.setIncomeType(17);
    }

    incomeModelDB.setAmount(income.getAmount());

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
  public String submitEntertainmentForm(@ModelAttribute("entertainment_mod_attribute") EntertainmentModel entModel) {
    // Temporary code
    System.out.println("Entertainment Registered: " + entModel.getOperDescription());
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
