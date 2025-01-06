package org.snorri1986.familybud;

public class Utils {

  public static int currencyConvert(String currValWeb) {
    int intCurValue = 0;
    switch (currValWeb) {
      case "DKK": intCurValue = 3; break;
      case "EUR": intCurValue = 1; break;
      case "UAH": intCurValue = 2; break;
      case "USD": intCurValue = 4; break;
    }
    return intCurValue;
  }
}
