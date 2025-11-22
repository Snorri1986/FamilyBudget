package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
}
