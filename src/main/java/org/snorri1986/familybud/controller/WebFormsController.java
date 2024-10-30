package org.snorri1986.familybud.controller;

import org.snorri1986.familybud.models.*;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebFormsController {

  @PostMapping("/registerIncome")
  public String submitIncomeForm(@ModelAttribute("income_mod_attribute") IncomeModel income) {
    // Temporary code
    System.out.println("Income Registered: " + income.getOperDescription());
    //TODO: add this page
    return "register_success";
  }

  @PostMapping("/registerEntertainment")
  public String submitEntertainmentForm(@ModelAttribute("entertainment_mod_attribute") EntertainmentModel entModel) {
    // Temporary code
    System.out.println("Entertainment Registered: " + entModel.getOperDescription());
    //TODO: add this page
    return "register_success";
  }

  @PostMapping("/registerGroceries")
  public String submitGroceriesForm(@ModelAttribute("groceries_mod_attribute") GroceriesModel grocModel) {
    // Temporary code
    System.out.println("Groceries purchase Registered: " + grocModel.getOperDescription());
    //TODO: add this page
    return "register_success";
  }

  @PostMapping("/registerHealth")
  public String submitHealthForm(@ModelAttribute("health_mod_attribute") HealthModel healthModel) {
    // Temporary code
    System.out.println("Health purchase Registered: " + healthModel.getOperDescription());
    //TODO: add this page
    return "register_success";
  }

  @PostMapping("/registerHousing")
  public String submitHousingForm(@ModelAttribute("rent_housing_mod_attribute") RentHousingModel rentHousingModel) {
    // Temporary code
    System.out.println("RentHousing purchase Registered: " + rentHousingModel.getOperDescription());
    //TODO: add this page
    return "register_success";
  }
}
