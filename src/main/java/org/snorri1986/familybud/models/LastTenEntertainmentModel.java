package org.snorri1986.familybud.models;

import java.time.ZonedDateTime;

public class LastTenEntertainmentModel {
  private long eventTypeId;
  private long amount;
  private long currency;
  private ZonedDateTime date;
  private long sourceCard;
  private String operType;
  private String comments;

  public LastTenEntertainmentModel(long eventTypeId, long amount, long currency, ZonedDateTime date, long sourceCard, String operType, String comments) {
    this.eventTypeId = eventTypeId;
    this.amount = amount;
    this.currency = currency;
    this.date = date;
    this.sourceCard = sourceCard;
    this.operType = operType;
    this.comments = comments;
  }

  public LastTenEntertainmentModel() {
  }

  public long getEventTypeId() {
    return eventTypeId;
  }

  public void setEventTypeId(long eventTypeId) {
    this.eventTypeId = eventTypeId;
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
    return "LastTenEntertainmentModel{" +
            "eventTypeId=" + eventTypeId +
            ", amount=" + amount +
            ", currency=" + currency +
            ", date=" + date +
            ", sourceCard=" + sourceCard +
            ", operType='" + operType + '\'' +
            ", comments='" + comments + '\'' +
            '}';
  }
}
