package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LastTenAtmOperationsModel {
  private String operType;
  private int amount;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private int currency;
  private String operDescription;

  public LastTenAtmOperationsModel(String operType, int amount, Date transactionDate, int currency, String operDescription) {
    this.operType = operType;
    this.amount = amount;
    this.transactionDate = transactionDate;
    this.currency = currency;
    this.operDescription = operDescription;
  }

  public String getOperType() {
    return operType;
  }

  public int getAmount() {
    return amount;
  }

  public int getCurrency() {
    return currency;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public String getOperDescription() {
    return operDescription;
  }

  public void setOperType(String operType) {
    this.operType = operType;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public void setCurrency(int currency) {
    this.currency = currency;
  }

  public void setOperDescription(String operDescription) {
    this.operDescription = operDescription;
  }

  @Override
  public String toString() {
    return "LastTenAtmOperationsModel{" +
            "operType='" + operType + '\'' +
            ", amount=" + amount +
            ", transactionDate=" + transactionDate +
            ", currency=" + currency +
            ", operDescription='" + operDescription + '\'' +
            '}';
  }
}
