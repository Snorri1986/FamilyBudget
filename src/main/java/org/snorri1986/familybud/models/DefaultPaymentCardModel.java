package org.snorri1986.familybud.models;

public class DefaultPaymentCardModel {
  private String cardNumber;

  public DefaultPaymentCardModel(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public String toString() {
    return "DefaultPaymentCardModel{" +
            "cardNumber='" + cardNumber + '\'' +
            '}';
  }
}
