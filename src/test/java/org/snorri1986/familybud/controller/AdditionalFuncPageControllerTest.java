package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AdditionalFuncPageControllerTest {

  @InjectMocks
  private AdditionalFuncPageController additionalFuncPageController;

  @Mock
  private Model model;

  @Test
  void testGetAtmCashPage() {
    String viewName = additionalFuncPageController.getAtmCashPage(model);
    assertEquals("atm_cash", viewName);
  }
}
