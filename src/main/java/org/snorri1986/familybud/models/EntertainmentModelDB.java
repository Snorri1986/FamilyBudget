package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EntertainmentModelDB {
  private int eventType;
  private int amount;
  private int currency;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private int cardNum;
  private String operDescription;

  public int getEventType() {
    return eventType;
  }

  public void setEventType(int eventType) {
    this.eventType = eventType;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getCurrency() {
    return currency;
  }

  public void setCurrency(int currency) {
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