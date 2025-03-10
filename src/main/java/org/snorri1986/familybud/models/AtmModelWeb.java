package org.snorri1986.familybud.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AtmModelWeb {
  private String operType;
  private int amount;
  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
  private Date transactionDate;
  private String operDescription;

  public String getOperType() {
    return operType;
  }

  public void setOperType(String operType) {
    this.operType = operType;
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

  public String getOperDescription() {
    return operDescription;
  }

  public void setOperDescription(String operDescription) {
    this.operDescription = operDescription;
  }

  @Override
  public String toString() {
    return "AtmModelWeb{" +
            "operType='" + operType + '\'' +
            ", amount=" + amount +
            ", transactionDate=" + transactionDate +
            ", operDescription='" + operDescription + '\'' +
            '}';
  }
}
