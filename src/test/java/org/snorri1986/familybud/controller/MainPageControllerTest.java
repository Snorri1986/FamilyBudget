package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snorri1986.familybud.models.*;
import org.snorri1986.familybud.service.DBService;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MainPageControllerTest {

  @InjectMocks
  private MainPageController mainPageController;

  @Mock
  private Model model;

  @Mock
  private DBService dbService;

  @Test
  void testGetLoginPage() {
    String viewName = mainPageController.getLoginPage(model);
    assertEquals("login", viewName);
  }

  @Test
  void testGetMainPage() {
    String viewName = mainPageController.getMainPage(model);
    assertEquals("main", viewName);
    verify(model).addAttribute(eq("theDate"), any(LocalDateTime.class));
  }

  @Test
  void testGetIncomePage() {
    List<LastTenIncomesModel> mockLastTenIncomes = List.of(new LastTenIncomesModel(), new LastTenIncomesModel());
    when(dbService.getLastTenIncomes()).thenReturn(mockLastTenIncomes);
    String viewName = mainPageController.getIncomePage(model);
    assertEquals("income", viewName);
    verify(model).addAttribute(eq("income_mod_attribute"), any(IncomeModelWeb.class));
    verify(model).addAttribute(eq("incomes"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("incomesLastTen", mockLastTenIncomes);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetEntertainmentPage() {
    List<LastTenEntertainmentModel> mockLastTenEntertainment = List.of(new LastTenEntertainmentModel(), new LastTenEntertainmentModel());
    when(dbService.getLastTenEntertainmentOperations()).thenReturn(mockLastTenEntertainment);
    String viewName = mainPageController.getEntertainmentPage(model);
    assertEquals("entertainment", viewName);
    verify(model).addAttribute(eq("entertainment_mod_attribute"), any(EntertainmentModelWeb.class));
    verify(model).addAttribute(eq("entList"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("entertainmentLastTen", mockLastTenEntertainment);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetGroceriesPage() {
    List<LastTenGroceriesOperModel> mockLastTenGroceries = List.of(new LastTenGroceriesOperModel(), new LastTenGroceriesOperModel());
    when(dbService.getLastTenGroceriesOperations()).thenReturn(mockLastTenGroceries);
    String viewName = mainPageController.getGroceriesPage(model);
    assertEquals("groceries", viewName);
    verify(model).addAttribute(eq("groceries_mod_attribute"), any(GroceriesModelWeb.class));
    verify(model).addAttribute(eq("purchesList"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("groceriesLastTen", mockLastTenGroceries);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetHealthPage() {
    List<LastTenHealthOperationsModel> mockLastTenHealth = List.of(new LastTenHealthOperationsModel(), new LastTenHealthOperationsModel());
    when(dbService.getLastTenHealthOperations()).thenReturn(mockLastTenHealth);
    String viewName = mainPageController.getHealthPage(model);
    assertEquals("health", viewName);
    verify(model).addAttribute(eq("health_mod_attribute"), any(HealthModelWeb.class));
    verify(model).addAttribute(eq("healthTypeList"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("healthLastTen", mockLastTenHealth);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testRentHousingPage() {
    List<LastTenHousingRentOperationsModel> mockLastTenRentHousing = List.of(new LastTenHousingRentOperationsModel(), new LastTenHousingRentOperationsModel());
    when(dbService.getLastTenHousingRentOperations()).thenReturn(mockLastTenRentHousing);
    String viewName = mainPageController.getRentHousingPage(model);
    assertEquals("renthousing", viewName);
    verify(model).addAttribute(eq("rent_housing_mod_attribute"), any(RentHousingModelWeb.class));
    verify(model).addAttribute(eq("housingTypes"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("rentHousingLastTen", mockLastTenRentHousing);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetTelecomPage() {
    String viewName = mainPageController.getTelecomPage(model);
    assertEquals("telecom", viewName);
    verify(model).addAttribute(eq("telecom_mod_attribute"), any(TelecomModelWeb.class));
    verify(model).addAttribute(eq("telecomOperations"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
  }

  @Test
  void testGetTravelPage() {
    String viewName = mainPageController.getTravelPage(model);
    assertEquals("travel", viewName);
    verify(model).addAttribute(eq("travel_mod_attribute"), any(TravelModelWeb.class));
    verify(model).addAttribute(eq("travelActivities"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
  }

  @Test
  void testGetAdditionalFunctionPage() {
    String viewName = mainPageController.getAdditionalFunctionsPage(model);
    assertEquals("additional", viewName);
  }
}
