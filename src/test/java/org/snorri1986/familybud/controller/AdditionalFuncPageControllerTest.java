package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.models.DefaultPaymentCardModel;
import org.snorri1986.familybud.models.TravelReportRequestModel;
import org.snorri1986.familybud.models.TravelReportResponseModel;
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
}
