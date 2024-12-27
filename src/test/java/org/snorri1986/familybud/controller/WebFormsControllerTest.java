package org.snorri1986.familybud.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.models.IncomeModelDB;
import org.snorri1986.familybud.models.IncomeModelWeb;
import org.snorri1986.familybud.service.DBService;
import java.time.LocalDate;


import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WebFormsControllerTest {

  @InjectMocks
  private WebFormsController webFormsController;

  @Mock
  private DBService dbService;

  /*@Mock
  private Utils utils;*/

  @Test
  public void testSubmitIncomeForm() {
    // Arrange
    IncomeModelWeb income = new IncomeModelWeb();
    income.setIncomeType("Salary");
    income.setAmount(5000);
    income.setCurrency("USD");
    income.setTransactionDate(convertToDate("25.12.2023 11:00"));
    income.setCardNum(6825);
    income.setOperDescription("Monthly salary");

    //TODO: pass Date
    //Mockito.when(Utils.currencyConvert("USD")).thenReturn(4);

    ArgumentCaptor<IncomeModelDB> captor = ArgumentCaptor.forClass(IncomeModelDB.class);

    // Act
    String result = webFormsController.submitIncomeForm(income);

    // Assert
    Mockito.verify(dbService).insertNewIncome(captor.capture());
    IncomeModelDB capturedIncome = captor.getValue();

    assertEquals(17, capturedIncome.getIncomeType());
    assertEquals(5000, capturedIncome.getAmount());
    assertEquals(4, capturedIncome.getCurrency());
    assertEquals("25.12.2023 11:00", capturedIncome.getTransactionDate());
    assertEquals(6825, capturedIncome.getCardNum());
    assertEquals("Monthly salary", capturedIncome.getOperDescription());
    assertEquals("s_income", result);
  }

  private Date convertToDate(String dateInString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    LocalDate localDate = LocalDate.parse(dateInString, formatter);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return date;
  }

  /*public int currencyConvert(String currValWeb) {
    int intCurValue = 0;
    switch (currValWeb) {
      case "DKK": intCurValue = 3;
      case "EUR": intCurValue = 1;
      case "UAH": intCurValue = 2;
      case "USD": intCurValue = 4;
    }
    return intCurValue;
  }*/
}
