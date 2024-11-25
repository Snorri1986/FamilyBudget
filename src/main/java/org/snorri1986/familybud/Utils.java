package org.snorri1986.familybud;

public class Utils {

  public static int currencyConvert(String currValWeb) {
    int intCurValue = 0;
    switch (currValWeb) {
      case "DKK": intCurValue = 3;
      case "EUR": intCurValue = 1;
      case "UAH": intCurValue = 2;
      case "USD": intCurValue = 4;
    }
    return intCurValue;
  }
}
