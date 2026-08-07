package org.snorri1986.familybud.models;

public class ExpenseModel {
  private int totalAmount;

  public ExpenseModel() {}

  public ExpenseModel(int totalAmount) {
    if  (totalAmount < 0) {
      throw new IllegalArgumentException("totalAmount must be a non-negative number");
    }
    this.totalAmount = totalAmount;
  }

  public int getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    if  (totalAmount < 0) {
      throw new IllegalArgumentException("totalAmount must be a non-negative number");
    }
    this.totalAmount = totalAmount;
  }

  @Override
  public String toString() {
    return "ExpenseModel{" +
            "totalAmount=" + totalAmount +
            '}';
  }
}
