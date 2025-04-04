package org.snorri1986.familybud.models;

import java.time.ZonedDateTime;

public class LastTenTravelOperationsModel {
  private long travelType;
  private long amount;
  private long currency;
  private ZonedDateTime date;
  private long sourceCard;
  private String destination;
  private String operType;
  private String comments;

  public LastTenTravelOperationsModel(long travelType, long amount, long currency, ZonedDateTime date, long sourceCard, String destination, String operType, String comments) {
    this.travelType = travelType;
    this.amount = amount;
    this.currency = currency;
    this.date = date;
    this.sourceCard = sourceCard;
    this.destination = destination;
    this.operType = operType;
    this.comments = comments;
  }

  public long getTravelType() {
    return travelType;
  }

  public void setTravelType(long travelType) {
    this.travelType = travelType;
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

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
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
    return "LastTenTravelOperationsModel{" +
            "travelType=" + travelType +
            ", amount=" + amount +
            ", currency=" + currency +
            ", date=" + date +
            ", sourceCard=" + sourceCard +
            ", destination='" + destination + '\'' +
            ", operType='" + operType + '\'' +
            ", comments='" + comments + '\'' +
            '}';
  }
}
