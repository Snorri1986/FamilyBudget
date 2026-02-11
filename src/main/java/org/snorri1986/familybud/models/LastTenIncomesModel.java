package org.snorri1986.familybud.models;

import java.time.ZonedDateTime;

public class LastTenIncomesModel {
  private long iType;
  private long amount;
  private long currency;
  private ZonedDateTime date;
  private long targetCard;
  private String operType;
  private String comments;

  public LastTenIncomesModel(long iType, long amount, long currency, ZonedDateTime date, long targetCard, String comments) {
    this.iType = iType;
    this.amount = amount;
    this.currency = currency;
    this.date = date;
    this.targetCard = targetCard;
    this.comments = comments;
  }

  // for last 10 cash operations
  public LastTenIncomesModel(long iType, long amount, long currency, ZonedDateTime date,String operType, String comments) {
    this.iType = iType;
    this.amount = amount;
    this.currency = currency;
    this.date = date;
    this.operType = operType;
    this.comments = comments;
  }

  public LastTenIncomesModel() {

  }

  public long getiType() {
    return iType;
  }

  public void setiType(long iType) {
    this.iType = iType;
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

  public String getOperType() {
    return operType;
  }

  public void setOperType(String operType) {
    this.operType = operType;
  }

  public long getTargetCard() {
    return targetCard;
  }

  public void setTargetCard(long targetCard) {
    this.targetCard = targetCard;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "LastTenIncomesModel{" +
            "iType=" + iType +
            ", amount=" + amount +
            ", currency=" + currency +
            ", date=" + date +
            ", targetCard=" + targetCard +
            ", comments='" + comments + '\'' +
            '}';
  }
}
