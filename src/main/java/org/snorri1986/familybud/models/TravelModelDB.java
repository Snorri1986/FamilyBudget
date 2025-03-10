package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TravelModelDB {
  private int travelType;
  private int amount;
  private int currency;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private String transactionType;
  private int cardNum;
  private String destination;
  private String operDescription;

  public int getTravelType() {
    return travelType;
  }

  public void setTravelType(int travelType) {
    this.travelType = travelType;
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

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public int getCardNum() {
    return cardNum;
  }

  public void setCardNum(int cardNum) {
    this.cardNum = cardNum;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getOperDescription() {
    return operDescription;
  }

  public void setOperDescription(String operDescription) {
    this.operDescription = operDescription;
  }

  @Override
  public String toString() {
    return "TravelModelDB{" +
            "travelType=" + travelType +
            ", amount=" + amount +
            ", currency=" + currency +
            ", transactionDate=" + transactionDate +
            ", transactionType='" + transactionType + '\'' +
            ", cardNum=" + cardNum +
            ", destination='" + destination + '\'' +
            ", operDescription='" + operDescription + '\'' +
            '}';
  }
}
