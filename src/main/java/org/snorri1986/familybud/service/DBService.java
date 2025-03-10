package org.snorri1986.familybud.service;

import org.snorri1986.familybud.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void insertNewIncome(IncomeModelDB incomeModel) {
    String sql = "SELECT public.i_income(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{incomeModel.getIncomeType(),
                                                  incomeModel.getAmount(),
                                                  incomeModel.getCurrency(),
                                                  incomeModel.getTransactionDate(),
                                                  incomeModel.getTransactionType(),
                                                  incomeModel.getCardNum(),
                                                  incomeModel.getOperDescription()},String.class);

  }

  public void insertNewEntertainment(EntertainmentModelDB entertainmentModelDB) {
    String sql = "SELECT public.i_entertainment(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{entertainmentModelDB.getEventType(),
            entertainmentModelDB.getAmount(),
            entertainmentModelDB.getCurrency(),
            entertainmentModelDB.getTransactionDate(),
            entertainmentModelDB.getTransactionType(),
            entertainmentModelDB.getCardNum(),
            entertainmentModelDB.getOperDescription()},String.class);
  }

  public void insertNewGroceries(GroceriesModelDB groceriesModelDB) {
    String sql = "SELECT public.i_groceries(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{groceriesModelDB.getPurchesType(),
            groceriesModelDB.getAmount(),
            groceriesModelDB.getCurrency(),
            groceriesModelDB.getTransactionDate(),
            groceriesModelDB.getTransactionType(),
            groceriesModelDB.getCardNum(),
            groceriesModelDB.getOperDescription()},String.class);

  }

  public void insertNewHealth(HealthModelDB healthModelDB) {
    String sql = "SELECT public.i_health(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{healthModelDB.getHealthOperType(),
            healthModelDB.getAmount(),
            healthModelDB.getCurrency(),
            healthModelDB.getTransactionDate(),
            healthModelDB.getTransactionType(),
            healthModelDB.getCardNum(),
            healthModelDB.getOperDescription()},String.class);
  }

  public void insertNewRentHousing(RentHousingModelDB rentHousingModelDB) {
    String sql = "SELECT public.i_housing_rent(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{rentHousingModelDB.getHousingType(),
            rentHousingModelDB.getAmount(),
            rentHousingModelDB.getCurrency(),
            rentHousingModelDB.getTransactionDate(),
            rentHousingModelDB.getTransactionType(),
            rentHousingModelDB.getCardNum(),
            rentHousingModelDB.getOperDescription()},String.class);
  }

  public void insertNewTelecom(TelecomModelDB telecomModelDB) {
    String sql = "SELECT public.i_telecom(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{telecomModelDB.getTelecomType(),
            telecomModelDB.getAmount(),
            telecomModelDB.getCurrency(),
            telecomModelDB.getTransactionDate(),
            telecomModelDB.getTransactionType(),
            telecomModelDB.getCardNum(),
            telecomModelDB.getOperDescription()},String.class);
  }

  public void insertNewTravel(TravelModelDB travelModelDB) {
    String sql = "SELECT public.i_travel(?,?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{travelModelDB.getTravelType(),
            travelModelDB.getAmount(),
            travelModelDB.getCurrency(),
            travelModelDB.getTransactionDate(),
            travelModelDB.getTransactionType(),
            travelModelDB.getCardNum(),
            travelModelDB.getDestination(),
            travelModelDB.getOperDescription()},String.class);
  }

  public int checkLogin(UserModel userModel) {
    String sql = "SELECT public.i_login(?,?)";
    int loginResult = jdbcTemplate.queryForObject(
            sql, new Object[]{
                    userModel.getUsername(),
                    userModel.getPassword()
            }, Integer.class);
    return loginResult;
  }

  public void insertNewAtmCash(AtmModelWeb atmModelWeb) {
    String sql = "SELECT public.atm_cash_register(?,?,?,?)";
    jdbcTemplate.queryForObject(sql,new Object[]{
            atmModelWeb.getOperType(),
            atmModelWeb.getAmount(),
            atmModelWeb.getTransactionDate(),
            atmModelWeb.getOperDescription()
    },String.class);
  }

  public int getCashBalance() {
    String sql = "SELECT public.get_cash_balance()";
    return jdbcTemplate.queryForObject(sql,Integer.class);
  }
}
