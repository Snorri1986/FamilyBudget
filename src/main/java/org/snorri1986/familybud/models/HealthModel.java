package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HealthModel {
  private String healthOperType;
  private int amount;
  private String currency;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private int cardNum;
  private String operDescription;

  public String getHealthOperType() {
    return healthOperType;
  }

  public void setHealthOperType(String healthOperType) {
    this.healthOperType = healthOperType;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public int getCardNum() {
    return cardNum;
  }

  public void setCardNum(int cardNum) {
    this.cardNum = cardNum;
  }

  public String getOperDescription() {
    return operDescription;
  }

  public void setOperDescription(String operDescription) {
    this.operDescription = operDescription;
  }
}
