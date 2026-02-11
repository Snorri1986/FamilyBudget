package org.snorri1986.familybud.models;

import java.time.ZonedDateTime;

public class LastTenHousingRentOperationsModel {
  private long housingRentType;
  private long amount;
  private long currency;
  private ZonedDateTime date;
  private long sourceCard;
  private String operType;
  private String comments;

  public LastTenHousingRentOperationsModel(long housingRentType, long amount, long currency, ZonedDateTime date, long sourceCard, String operType, String comments) {
    this.housingRentType = housingRentType;
    this.amount = amount;
    this.currency = currency;
    this.date = date;
    this.sourceCard = sourceCard;
    this.operType = operType;
    this.comments = comments;
  }

  public LastTenHousingRentOperationsModel(long housingRentType, long amount, long currency, ZonedDateTime date, String operType, String comments) {
    this.housingRentType = housingRentType;
    this.amount = amount;
    this.currency = currency;
    this.date = date;
    this.operType = operType;
    this.comments = comments;
  }

  public LastTenHousingRentOperationsModel() {
  }

  public long getHousingRentType() {
    return housingRentType;
  }

  public void setHousingRentType(long housingRentType) {
    this.housingRentType = housingRentType;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public long getCurrency() {
    return currency;
  }

  public void setCurrency(long currency) {
    this.currency = currency;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }

  public long getSourceCard() {
    return sourceCard;
  }

  public void setSourceCard(long sourceCard) {
    this.sourceCard = sourceCard;
  }

  public String getOperType() {
    return operType;
  }

  public void setOperType(String operType) {
    this.operType = operType;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "LastTenHousingRentOperations{" +
            "housingRentType=" + housingRentType +
            ", amount=" + amount +
            ", currency=" + currency +
            ", date=" + date +
            ", sourceCard=" + sourceCard +
            ", operType='" + operType + '\'' +
            ", comments='" + comments + '\'' +
            '}';
  }
}
