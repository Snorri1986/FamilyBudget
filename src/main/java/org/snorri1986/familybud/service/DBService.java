package org.snorri1986.familybud.service;

import org.snorri1986.familybud.models.IncomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void insertNewIncome(IncomeModel incomeModel) {
    String sql = "SELECT public.i_income(?,?,?,?,?,?)";
    jdbcTemplate.queryForObject(sql, new Object[]{incomeModel.getIncomeType(),
                                                  incomeModel.getAmount(),
                                                  incomeModel.getCurrency(),
                                                  incomeModel.getTransactionDate(),
                                                  incomeModel.getCardNum(),
                                                  incomeModel.getOperDescription()},String.class);

  }
}
