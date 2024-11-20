package org.snorri1986.familybud.service;

import org.snorri1986.familybud.models.EntertainmentModelDB;
import org.snorri1986.familybud.models.IncomeModelDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void insertNewIncome(IncomeModelDB incomeModel) {
    String sql = "SELECT public.i_income(?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{incomeModel.getIncomeType(),
                                                  incomeModel.getAmount(),
                                                  incomeModel.getCurrency(),
                                                  incomeModel.getTransactionDate(),
                                                  incomeModel.getCardNum(),
                                                  incomeModel.getOperDescription()},String.class);

  }

  public void insertNewEntertainment(EntertainmentModelDB entertainmentModelDB) {
    String sql = "SELECT public.i_entertainment(?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{entertainmentModelDB.getEventType(),
            entertainmentModelDB.getAmount(),
            entertainmentModelDB.getCurrency(),
            entertainmentModelDB.getTransactionDate(),
            entertainmentModelDB.getCardNum(),
            entertainmentModelDB.getOperDescription()},String.class);
  }
}
