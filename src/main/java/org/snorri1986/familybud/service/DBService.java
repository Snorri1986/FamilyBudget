package org.snorri1986.familybud.service;

import org.snorri1986.familybud.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;

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
            incomeModel.getOperDescription()}, String.class);

  }

  public void insertNewEntertainment(EntertainmentModelDB entertainmentModelDB) {
    String sql = "SELECT public.i_entertainment(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{entertainmentModelDB.getEventType(),
            entertainmentModelDB.getAmount(),
            entertainmentModelDB.getCurrency(),
            entertainmentModelDB.getTransactionDate(),
            entertainmentModelDB.getTransactionType(),
            entertainmentModelDB.getCardNum(),
            entertainmentModelDB.getOperDescription()}, String.class);
  }

  public void insertNewGroceries(GroceriesModelDB groceriesModelDB) {
    String sql = "SELECT public.i_groceries(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{groceriesModelDB.getPurchesType(),
            groceriesModelDB.getAmount(),
            groceriesModelDB.getCurrency(),
            groceriesModelDB.getTransactionDate(),
            groceriesModelDB.getTransactionType(),
            groceriesModelDB.getCardNum(),
            groceriesModelDB.getOperDescription()}, String.class);

  }

  public void insertNewHealth(HealthModelDB healthModelDB) {
    String sql = "SELECT public.i_health(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{healthModelDB.getHealthOperType(),
            healthModelDB.getAmount(),
            healthModelDB.getCurrency(),
            healthModelDB.getTransactionDate(),
            healthModelDB.getTransactionType(),
            healthModelDB.getCardNum(),
            healthModelDB.getOperDescription()}, String.class);
  }

  public void insertNewRentHousing(RentHousingModelDB rentHousingModelDB) {
    String sql = "SELECT public.i_housing_rent(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{rentHousingModelDB.getHousingType(),
            rentHousingModelDB.getAmount(),
            rentHousingModelDB.getCurrency(),
            rentHousingModelDB.getTransactionDate(),
            rentHousingModelDB.getTransactionType(),
            rentHousingModelDB.getCardNum(),
            rentHousingModelDB.getOperDescription()}, String.class);
  }

  public void insertNewTelecom(TelecomModelDB telecomModelDB) {
    String sql = "SELECT public.i_telecom(?,?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{telecomModelDB.getTelecomType(),
            telecomModelDB.getAmount(),
            telecomModelDB.getCurrency(),
            telecomModelDB.getTransactionDate(),
            telecomModelDB.getTransactionType(),
            telecomModelDB.getCardNum(),
            telecomModelDB.getOperDescription()}, String.class);
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
            travelModelDB.getOperDescription()}, String.class);
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
    jdbcTemplate.queryForObject(sql, new Object[]{
            atmModelWeb.getOperType(),
            atmModelWeb.getAmount(),
            atmModelWeb.getTransactionDate(),
            atmModelWeb.getOperDescription()
    }, String.class);
  }

  public int getCashBalance() {
    String sql = "SELECT public.get_cash_balance()";
    return jdbcTemplate.queryForObject(sql, Integer.class);
  }

  public List<LastTenIncomesModel> getLastTenIncomes() {
    String sql = "SELECT * FROM get_last_ten_incomes()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenIncomesModel>() {
      @Override
      public LastTenIncomesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenIncomesModel(rs.getLong("i_type"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("target_card"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenCashOperModel> getLastTenCashOperations() {
    String sql = "SELECT * FROM get_last_ten_cash_oper()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenCashOperModel>() {
      @Override
      public LastTenCashOperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenCashOperModel(rs.getLong("optype"),
                rs.getLong("amount"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenEntertainmentModel> getLastTenEntertainmentOperations() {
    String sql = "SELECT * FROM get_last_ten_entertainment()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenEntertainmentModel>() {
      @Override
      public LastTenEntertainmentModel mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new LastTenEntertainmentModel(rs.getLong("event_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("operType"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenGroceriesOperModel> getLastTenGroceriesOperations() {
    String sql = "SELECT * FROM get_last_ten_groceries_oper()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenGroceriesOperModel>() {
      @Override
      public LastTenGroceriesOperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenGroceriesOperModel(rs.getLong("g_type"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenHealthOperations> getLastTenHealthOperations() {
    String sql = "SELECT * FROM get_last_ten_health_oper()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenHealthOperations>() {
      @Override
      public LastTenHealthOperations mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenHealthOperations(rs.getLong("h_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenHousingRentOperations> getLastTenHousingRentOperations() {
    String sql = "SELECT * FROM get_last_ten_housing_oper()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenHousingRentOperations>() {
      @Override
      public LastTenHousingRentOperations mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new LastTenHousingRentOperations(rs.getLong("hr_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenTelecomOperationsModel> getLastTenTelecomOperations() {
    String sql = "SELECT * from public.get_last_telecom_operations()";
    return jdbcTemplate.query(sql,new RowMapper<LastTenTelecomOperationsModel>() {
      @Override
      public LastTenTelecomOperationsModel mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new LastTenTelecomOperationsModel(rs.getLong("t_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenTravelOperationsModel> getLastTenTravelOperations() {
    String sql = "SELECT * from public.get_last_ten_travel_operations()";
    return jdbcTemplate.query(sql,new RowMapper<LastTenTravelOperationsModel>() {
      @Override
      public LastTenTravelOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenTravelOperationsModel(rs.getLong("tr_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("destination"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }
}



