package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.models.DefaultPaymentCardModel;
import org.snorri1986.familybud.service.DBService;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
