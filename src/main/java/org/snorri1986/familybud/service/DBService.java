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

  public List<LastTenIncomesModel> getLastTenIncomesCard() {
    String sql = "SELECT * FROM get_last_ten_incomes_card()";
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

  public List<LastTenIncomesModel> getLastTenIncomesCash() {
    String sql = "SELECT * FROM get_last_ten_incomes_cash()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenIncomesModel>() {
      @Override
      public LastTenIncomesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenIncomesModel(rs.getLong("ex_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("opertype"),
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

  public List<LastTenEntertainmentModel> getLastTenEntertainmentOperationsCard() {
    String sql = "SELECT * FROM get_last_ten_entertainment_card()";
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

  public List<LastTenEntertainmentModel> getLastTenEntertainmentOperationsCash() {
    String sql = "SELECT * from get_last_ten_entertainment_cash()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenEntertainmentModel>() {
      @Override
      public LastTenEntertainmentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenEntertainmentModel(rs.getLong("ex_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("opertype"),  // always NULL
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenGroceriesOperModel> getLastTenGroceriesOperationsCard() {
    String sql = "SELECT * FROM get_last_ten_groceries_card()";
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

  public List<LastTenGroceriesOperModel> getLastTenGroceriesOperationsCash() {
    String sql = "SELECT * FROM get_last_ten_groceries_cash()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenGroceriesOperModel>() {
      @Override
      public LastTenGroceriesOperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenGroceriesOperModel(rs.getLong("ex_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenHealthOperationsModel> getLastTenHealthOperationsCard() {
    String sql = "SELECT * FROM get_last_ten_health_oper_card()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenHealthOperationsModel>() {
      @Override
      public LastTenHealthOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenHealthOperationsModel(rs.getLong("h_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenHealthOperationsModel> getLastTenHealthOperationsCash() {
    String sql = "SELECT * from get_last_ten_health_oper_cash()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenHealthOperationsModel>() {
      @Override
      public LastTenHealthOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenHealthOperationsModel(rs.getLong("h_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("opertype"),  // always NULL
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenHousingRentOperationsModel> getLastTenHousingRentOperationsCard() {
    String sql = "SELECT * FROM get_last_ten_housing_card()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenHousingRentOperationsModel>() {
      @Override
      public LastTenHousingRentOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenHousingRentOperationsModel(rs.getLong("hr_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("source_card"),
                rs.getString("opertype"),
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenHousingRentOperationsModel> getLastTenHousingRentOperationsCash() {
    String sql = "SELECT * FROM get_last_ten_housing_cash()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenHousingRentOperationsModel>() {
      @Override
      public LastTenHousingRentOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenHousingRentOperationsModel(rs.getLong("h_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("opertype"),  // always NULL
                rs.getString("comments"));
      }
    });
  }


  public List<LastTenTelecomOperationsModel> getLastTenTelecomOperationsCard() {
    String sql = "SELECT * from public.get_last_ten_telecom_card()";
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

  public List<LastTenTelecomOperationsModel> getLastTenTelecomOperationsCash() {
    String sql = "SELECT * from public.get_last_ten_telecom_cash()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenTelecomOperationsModel>() {
      @Override
      public LastTenTelecomOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenTelecomOperationsModel(rs.getLong("h_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("opertype"),  // always NULL
                rs.getString("comments"));
      }
    });
  }


  public List<LastTenTravelOperationsModel> getLastTenTravelOperationsCard() {
    String sql = "SELECT * from public.get_last_ten_travel_card()";
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

  public List<LastTenTravelOperationsModel> getLastTenTravelOperationsCash() {
    String sql = "SELECT * from public.get_last_ten_travel_cash()";
    return jdbcTemplate.query(sql,new RowMapper<LastTenTravelOperationsModel>() {
      @Override
      public LastTenTravelOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenTravelOperationsModel(rs.getLong("h_type_id"),
                rs.getLong("amount"),
                rs.getLong("currency"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getString("destination"),
                rs.getString("opertype"),  // always NULL
                rs.getString("comments"));
      }
    });
  }

  public List<LastTenAtmOperationsModel> getLastTenAtmOperations() {
    String sql = "SELECT * from public.get_last_ten_atm()";
    return jdbcTemplate.query(sql, new RowMapper<LastTenAtmOperationsModel>() {
      @Override
      public LastTenAtmOperationsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LastTenAtmOperationsModel(rs.getLong("optype"),
                rs.getLong("amount"),
                rs.getTimestamp("date").toInstant().atZone(ZoneId.systemDefault()),
                rs.getLong("currency"),
                rs.getString("comments"));
      }
    });
  }
}



