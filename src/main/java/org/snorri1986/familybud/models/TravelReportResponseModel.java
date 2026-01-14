package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public class TravelReportResponseModel {
  private int travelExpenseTypeID;
  private int amount;
  private int currency;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private ZonedDateTime transactionDate;
  private int sourceCard;
  private String comments;

  public TravelReportResponseModel() {}

  public TravelReportResponseModel(int travelExpenseTypeID, int amount, int currency, ZonedDateTime transactionDate, int sourceCard, String comments) {
    this.travelExpenseTypeID = travelExpenseTypeID;
    this.amount = amount;
    this.currency = currency;
    this.transactionDate = transactionDate;
    this.sourceCard = sourceCard;
    this.comments = comments;
  }

  public int getTravelExpenseTypeID() {
    return travelExpenseTypeID;
  }

  public void setTravelExpenseTypeID(int travelExpenseTypeID) {
    this.travelExpenseTypeID = travelExpenseTypeID;
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

  public ZonedDateTime getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(ZonedDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public int getSourceCard() {
    return sourceCard;
  }

  public void setSourceCard(int sourceCard) {
    this.sourceCard = sourceCard;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "TravelReportResponseModel{" +
            "travelExpenseTypeID=" + travelExpenseTypeID +
            ", amount=" + amount +
            ", currency=" + currency +
            ", transactionDate=" + transactionDate +
            ", sourceCard=" + sourceCard +
            ", comments='" + comments + '\'' +
            '}';
  }
}
