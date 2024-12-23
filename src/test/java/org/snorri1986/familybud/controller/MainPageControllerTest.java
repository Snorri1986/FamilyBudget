package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.models.EntertainmentModelWeb;
import org.snorri1986.familybud.models.IncomeModelWeb;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class MainPageControllerTest {

  @InjectMocks
  private MainPageController mainPageController;

  @Mock
  private Model model;

  @Test
  void testGetMainPage() {
    String viewName = mainPageController.getMainPage(model);
    assertEquals("main", viewName);
    verify(model).addAttribute(eq("theDate"), any(LocalDateTime.class));
  }

  @Test
  void testGetIncomePage() {
    String viewName = mainPageController.getIncomePage(model);
    assertEquals("income", viewName);
    verify(model).addAttribute(eq("income_mod_attribute"), any(IncomeModelWeb.class));
    verify(model).addAttribute(eq("incomes"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
  }

  @Test
  void testGetEntertainmentPage() {
    String viewName = mainPageController.getEntertainmentPage(model);
    assertEquals("entertainment", viewName);
    verify(model).addAttribute(eq("entertainment_mod_attribute"), any(EntertainmentModelWeb.class));
    verify(model).addAttribute(eq("entList"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
  }
}
