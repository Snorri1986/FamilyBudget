package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionalFuncPageControllerTest {

  @Mock
  DBService dbService;

  @InjectMocks
  private AdditionalFuncPageController additionalFuncPageController;

  @Mock
  private WebFormsController webFormsController;

  @Mock
  private Model model;

  @Test
  void testGetAtmCashPage() {
    String viewName = additionalFuncPageController.getLastTenAtmCashOperations(model);
    assertEquals("atm_cash", viewName);
  }

  @Test
  void testSubmitNewCardForm() {
    DefaultPaymentCardModel defaultPaymentCardModel = new DefaultPaymentCardModel();
    defaultPaymentCardModel.setCardNumber("1234");
    ArgumentCaptor<DefaultPaymentCardModel> captor = ArgumentCaptor.forClass(DefaultPaymentCardModel.class);
    String result = additionalFuncPageController.submitNewCardForm(defaultPaymentCardModel);
    Mockito.verify(dbService).insertNewDefaultPaymentCard(captor.capture());
    assertEquals("1234", captor.getValue().getCardNumber());
  }

  @Test
  void testGetPaymentCardDefault() {
    Mockito.when(dbService.getPaymentCardDefault()).thenReturn(1234);
    int card_result = dbService.getPaymentCardDefault();
    assertEquals(1234, card_result);
    Mockito.verify(dbService).getPaymentCardDefault();
  }

  @Test
  void testSetNewLocation() {
    LocationModel locationModelForTest = new LocationModel();
    locationModelForTest.setCity("Oslo");
    locationModelForTest.setCountry("Norway");
    locationModelForTest.setVat(15);
    ArgumentCaptor<LocationModel> captor = ArgumentCaptor.forClass(LocationModel.class);
    String result = additionalFuncPageController.submitNewLocation(locationModelForTest);
    Mockito.verify(dbService).insertNewLocation(captor.capture());
    assertEquals("Oslo", captor.getValue().getCity());
    assertEquals("Norway", captor.getValue().getCountry());
    assertEquals(15, captor.getValue().getVat());
  }

  @Test
  void testGetLocation() {
    Mockito.when(dbService.getLocation()).thenReturn(new LocationModel("Denmark","Copenhagen"));
    LocationModel locationMockDB = dbService.getLocation();
    assertEquals("Copenhagen", locationMockDB.getCity());
    assertEquals("Denmark", locationMockDB.getCountry());
    Mockito.verify(dbService).getLocation();
  }

  @Test
  void testGetTravelReportPage() {
    String viewName = additionalFuncPageController.getTravelReport(model);
    assertEquals("travel_report", viewName);
  }

  @Test
  void testGetTravelExpense() {
    TravelReportRequestModel travelReportRequestModel = new TravelReportRequestModel();
    travelReportRequestModel.setTravelDestination("Rodos");
    List<TravelReportResponseModel> mockTravelReportResponseModel = List.of(new TravelReportResponseModel(), new TravelReportResponseModel());
    ArgumentCaptor<TravelReportRequestModel> captor = ArgumentCaptor.forClass(TravelReportRequestModel.class);
    when(dbService.getTravelExpenseReportDB(captor.capture())).thenReturn(mockTravelReportResponseModel);
    String result = additionalFuncPageController.getTravelExpense(travelReportRequestModel,model);
    verify(model).addAttribute("reportList", mockTravelReportResponseModel);
    verify(model).addAttribute("SumUSD", mockTravelReportResponseModel.stream()
            .filter(r -> r.getCurrency() == 4) // USD
            .mapToInt(TravelReportResponseModel::getAmount)
            .sum());
    verify(model).addAttribute("SumEUR", mockTravelReportResponseModel.stream()
            .filter(r -> r.getCurrency() == 1) // EUR
            .mapToInt(TravelReportResponseModel::getAmount)
            .sum());
    verify(model).addAttribute("SumEUR", mockTravelReportResponseModel.stream()
            .filter(r -> r.getCurrency() == 3) // DKK
            .mapToInt(TravelReportResponseModel::getAmount)
            .sum());
    verify(model).addAttribute("SumEUR", mockTravelReportResponseModel.stream()
            .filter(r -> r.getCurrency() == 2) // UAH
            .mapToInt(TravelReportResponseModel::getAmount)
            .sum());
    assertEquals("travel_report", result);
  }

  @Test
  void testGetDailyExpenseReportByCurrencyEUR() {
    Mockito.when(dbService.getDailyExpenseReportByCurrency(1)).thenReturn(1000);
    int dailyExpenseReport = dbService.getDailyExpenseReportByCurrency(1);
    Mockito.verify(dbService).getDailyExpenseReportByCurrency(1);
  }

  @Test
  void testGetDailyExpenseReportByCurrencyUAH() {
    Mockito.when(dbService.getDailyExpenseReportByCurrency(2)).thenReturn(1000);
    int dailyExpenseReport = dbService.getDailyExpenseReportByCurrency(2);
    Mockito.verify(dbService).getDailyExpenseReportByCurrency(2);
  }

  @Test
  void testGetDailyExpenseReportByCurrencyDKK() {
    Mockito.when(dbService.getDailyExpenseReportByCurrency(3)).thenReturn(1000);
    int dailyExpenseReport = dbService.getDailyExpenseReportByCurrency(3);
    Mockito.verify(dbService).getDailyExpenseReportByCurrency(3);
  }

  @Test
  void testGetDailyExpenseReportByCurrencyUSD() {
    Mockito.when(dbService.getDailyExpenseReportByCurrency(4)).thenReturn(1000);
    int dailyExpenseReport = dbService.getDailyExpenseReportByCurrency(4);
    Mockito.verify(dbService).getDailyExpenseReportByCurrency(4);
  }

  @Test
  void testGetMonthlyExpenseReportByCurrencyEUR() {
    Mockito.when(dbService.getMonthlyExpenseReportByCurrency(1)).thenReturn(1000);
    int monthlyExpenseReport = dbService.getMonthlyExpenseReportByCurrency(1);
    Mockito.verify(dbService).getMonthlyExpenseReportByCurrency(1);
  }

  @Test
  void testGetMonthlyExpenseReportByCurrencyUAH() {
    Mockito.when(dbService.getMonthlyExpenseReportByCurrency(2)).thenReturn(1000);
    int monthlyExpenseReport = dbService.getMonthlyExpenseReportByCurrency(2);
    Mockito.verify(dbService).getMonthlyExpenseReportByCurrency(2);
  }

  @Test
  void testGetMonthlyExpenseReportByCurrencyDKK() {
    Mockito.when(dbService.getMonthlyExpenseReportByCurrency(3)).thenReturn(1000);
    int monthlyExpenseReport = dbService.getMonthlyExpenseReportByCurrency(3);
    Mockito.verify(dbService).getMonthlyExpenseReportByCurrency(3);
  }

  @Test
  void testGetMonthlyExpenseReportByCurrencyUSD() {
    Mockito.when(dbService.getMonthlyExpenseReportByCurrency(4)).thenReturn(1000);
    int monthlyExpenseReport = dbService.getMonthlyExpenseReportByCurrency(4);
    Mockito.verify(dbService).getMonthlyExpenseReportByCurrency(4);
  }

  @Test
  void testGetAnnualExpenseReportByCurrencyEUR() {
    Mockito.when(dbService.getAnnualExpenseReportByCurrency(1)).thenReturn(1000);
    int annualExpenseReport = dbService.getAnnualExpenseReportByCurrency(1);
    Mockito.verify(dbService).getAnnualExpenseReportByCurrency(1);
  }

  @Test
  void testGetAnnualExpenseReportByCurrencyUAH() {
    Mockito.when(dbService.getAnnualExpenseReportByCurrency(2)).thenReturn(1000);
    int annualExpenseReport = dbService.getAnnualExpenseReportByCurrency(2);
    Mockito.verify(dbService).getAnnualExpenseReportByCurrency(2);
  }

  @Test
  void testGetAnnualExpenseReportByCurrencyDKK() {
    Mockito.when(dbService.getAnnualExpenseReportByCurrency(3)).thenReturn(1000);
    int annualExpenseReport = dbService.getAnnualExpenseReportByCurrency(3);
    Mockito.verify(dbService).getAnnualExpenseReportByCurrency(3);
  }

  @Test
  void testGetAnnualExpenseReportByCurrencyUSD() {
    Mockito.when(dbService.getAnnualExpenseReportByCurrency(4)).thenReturn(1000);
    int annualExpenseReport = dbService.getAnnualExpenseReportByCurrency(4);
    Mockito.verify(dbService).getAnnualExpenseReportByCurrency(4);
  }

  @Test
  void testGetCashBalance() {
    Mockito.when(dbService.getCashBalance()).thenReturn(1000);
    int cashBalance = dbService.getCashBalance();
    Mockito.verify(dbService).getCashBalance();
    assertEquals(1000, cashBalance);
  }

  @Test
  void testGetCardBalance() {
    Mockito.when(dbService.getCardBalance()).thenReturn(1000);
    int cardBalance = dbService.getCardBalance();
    Mockito.verify(dbService).getCardBalance();
    assertEquals(1000, cardBalance);
  }

  @Test
  void testShowCardBalance() {
    String viewName = additionalFuncPageController.showCardBalance(model);
    assertEquals("card_balance", viewName);
  }

  @Test
  void testShowCashBalance() {
    String viewName = additionalFuncPageController.showCashBalance(model);
    assertEquals("cash_balance", viewName);
  }
}


