package org.snorri1986.familybud.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.Utils;
import org.snorri1986.familybud.models.EntertainmentModelDB;
import org.snorri1986.familybud.models.EntertainmentModelWeb;
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

    ArgumentCaptor<IncomeModelDB> captor = ArgumentCaptor.forClass(IncomeModelDB.class);

    // Act
    String result = webFormsController.submitIncomeForm(income);

    // Assert
    Mockito.verify(dbService).insertNewIncome(captor.capture());
    IncomeModelDB capturedIncome = captor.getValue();

    assertEquals(14, capturedIncome.getIncomeType());
    assertEquals(income.getAmount(), capturedIncome.getAmount());
    assertEquals(Utils.currencyConvert(income.getCurrency()), capturedIncome.getCurrency());
    assertEquals(income.getTransactionDate(), capturedIncome.getTransactionDate());
    assertEquals(income.getCardNum(), capturedIncome.getCardNum());
    assertEquals(income.getOperDescription(), capturedIncome.getOperDescription());
    assertEquals("s_income", result);
  }

  @Test
  public void testSubmitEntertainmentForm() {
    EntertainmentModelWeb entertainmentModelWeb = new EntertainmentModelWeb();
    entertainmentModelWeb.setEventType("Cinema");
    entertainmentModelWeb.setAmount(1000);
    entertainmentModelWeb.setCurrency("EUR");
    entertainmentModelWeb.setTransactionDate(convertToDate("30.12.2024 13:30"));
    entertainmentModelWeb.setCardNum(6285);
    entertainmentModelWeb.setOperDescription("Cinema in Herlev");

    ArgumentCaptor<EntertainmentModelDB> captor = ArgumentCaptor.forClass(EntertainmentModelDB.class);

    String result = webFormsController.submitEntertainmentForm(entertainmentModelWeb);

    Mockito.verify(dbService).insertNewEntertainment(captor.capture());
    EntertainmentModelDB capturedEntertainment = captor.getValue();
    assertEquals(13, capturedEntertainment.getEventType());
    assertEquals(entertainmentModelWeb.getAmount(), capturedEntertainment.getAmount());
    assertEquals(Utils.currencyConvert(entertainmentModelWeb.getCurrency()), capturedEntertainment.getCurrency());
    assertEquals(entertainmentModelWeb.getTransactionDate(), capturedEntertainment.getTransactionDate());
    assertEquals(entertainmentModelWeb.getCardNum(), capturedEntertainment.getCardNum());
    assertEquals(entertainmentModelWeb.getOperDescription(), capturedEntertainment.getOperDescription());
    assertEquals("s_entertainment", result);
  }

  private Date convertToDate(String dateInString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    LocalDate localDate = LocalDate.parse(dateInString, formatter);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return date;
  }
}
