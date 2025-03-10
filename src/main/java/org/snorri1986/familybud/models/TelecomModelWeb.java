package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TelecomModelWeb {
  private String telecomType;
  private int amount;
  private String currency;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private String transactionType;
  private int cardNum;
  private String operDescription;

  public String getTelecomType() {
    return telecomType;
  }

  public void setTelecomType(String telecomType) {
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

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
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
    return "TelecomModelWeb{" +
            "telecomType='" + telecomType + '\'' +
            ", amount=" + amount +
            ", currency='" + currency + '\'' +
            ", transactionDate=" + transactionDate +
            ", transactionType='" + transactionType + '\'' +
            ", cardNum=" + cardNum +
            ", operDescription='" + operDescription + '\'' +
            '}';
  }
}
