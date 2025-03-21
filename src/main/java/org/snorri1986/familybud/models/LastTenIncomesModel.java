package org.snorri1986.familybud.models;

import java.time.ZonedDateTime;

public class LastTenIncomesModel {
  private long iType;
  private long amount;
  private long currency;
  private ZonedDateTime date;
  private long targetCard;
  private String comments;

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
