package org.snorri1986.familybud.models;

import java.time.ZonedDateTime;

public class LastTenCashOperModel {
  private long opType;
  private long amount;
  private ZonedDateTime date;
  private String comments;

  public LastTenCashOperModel(long opType,long amount, ZonedDateTime date, String comments) {
    this.opType = opType;
    this.amount = amount;
    this.date = date;
    this.comments = comments;
  }

  public long getOpType() {
    return opType;
  }

  public void setOpType(long opType) {
    this.opType = opType;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "LastTenCashOperModel{" +
            "opType=" + opType +
            ", amount=" + amount +
            ", date=" + date +
            ", comments='" + comments + '\'' +
            '}';
  }
}
