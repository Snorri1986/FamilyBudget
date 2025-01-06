package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TelecomModelDB {
  private int telecomType;
  private int amount;
  private int currency;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private int cardNum;
  private String operDescription;

  public int getTelecomType() {
    return telecomType;
  }

  public void setTelecomType(int telecomType) {
    this.telecomType = telecomType;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public int getCurrency() {
    return currency;
  }

  public void setCurrency(int currency) {
    this.currency = currency;
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

  @Override
  public String toString() {
    return "TelecomModelDB{" +
            "telecomType=" + telecomType +
            ", amount=" + amount +
            ", currency=" + currency +
            ", transactionDate=" + transactionDate +
            ", cardNum=" + cardNum +
            ", operDescription='" + operDescription + '\'' +
            '}';
  }
}
