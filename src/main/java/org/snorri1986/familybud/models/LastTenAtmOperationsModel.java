package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public class LastTenAtmOperationsModel {
  private long operType;
  private long amount;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private ZonedDateTime transactionDate;
  private long currency;
  private String operDescription;

  public LastTenAtmOperationsModel(long operType, long amount, ZonedDateTime transactionDate, long currency, String operDescription) {
    this.operType = operType;
    this.amount = amount;
    this.transactionDate = transactionDate;
    this.currency = currency;
    this.operDescription = operDescription;
  }

  public long getOperType() {
    return operType;
  }

  public long getAmount() {
    return amount;
  }

  public ZonedDateTime getTransactionDate() {
    return transactionDate;
  }

  public long getCurrency() {
    return currency;
  }

  public String getOperDescription() {
    return operDescription;
  }

  public void setOperType(long operType) {
    this.operType = operType;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public void setTransactionDate(ZonedDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public void setCurrency(long currency) {
    this.currency = currency;
  }

  public void setOperDescription(String operDescription) {
    this.operDescription = operDescription;
  }

  @Override
  public String toString() {
    return "LastTenAtmOperationsModel{" +
            "operType=" + operType +
            ", amount=" + amount +
            ", transactionDate=" + transactionDate +
            ", currency=" + currency +
            ", operDescription='" + operDescription + '\'' +
            '}';
  }
}
