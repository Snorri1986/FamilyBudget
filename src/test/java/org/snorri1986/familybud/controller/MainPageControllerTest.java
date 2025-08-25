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
    List<LastTenIncomesModel> mockLastTenIncomesCard = List.of(new LastTenIncomesModel(), new LastTenIncomesModel());
    List<LastTenIncomesModel> mockLastTenIncomesCash = List.of(new LastTenIncomesModel(), new LastTenIncomesModel());
    when(dbService.getLastTenIncomesCard()).thenReturn(mockLastTenIncomesCard);
    when(dbService.getLastTenIncomesCash()).thenReturn(mockLastTenIncomesCash);
    String viewName = mainPageController.getIncomePage(model);
    assertEquals("income", viewName);
    verify(model).addAttribute(eq("income_mod_attribute"), any(IncomeModelWeb.class));
    verify(model).addAttribute(eq("incomes"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("incomesLastTenCard", mockLastTenIncomesCard);
    verify(model).addAttribute("incomesLastTenCash", mockLastTenIncomesCash);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetEntertainmentPage() {
    List<LastTenEntertainmentModel> mockLastTenEntertainmentCard = List.of(new LastTenEntertainmentModel(), new LastTenEntertainmentModel());
    List<LastTenEntertainmentModel> mockLastTenEntertainmentCash = List.of(new LastTenEntertainmentModel(), new LastTenEntertainmentModel());
    when(dbService.getLastTenEntertainmentOperationsCard()).thenReturn(mockLastTenEntertainmentCard);
    when(dbService.getLastTenEntertainmentOperationsCash()).thenReturn(mockLastTenEntertainmentCash);
    String viewName = mainPageController.getEntertainmentPage(model);
    assertEquals("entertainment", viewName);
    verify(model).addAttribute(eq("entertainment_mod_attribute"), any(EntertainmentModelWeb.class));
    verify(model).addAttribute(eq("entList"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("entertainmentLastTenCard", mockLastTenEntertainmentCard);
    verify(model).addAttribute("entertainmentLastTenCash", mockLastTenEntertainmentCash);
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
    List<LastTenHealthOperationsModel> mockLastTenHealthCard = List.of(new LastTenHealthOperationsModel(), new LastTenHealthOperationsModel());
    List<LastTenHealthOperationsModel> mockLastTenHealthCash = List.of(new LastTenHealthOperationsModel(), new LastTenHealthOperationsModel());
    when(dbService.getLastTenHealthOperationsCard()).thenReturn(mockLastTenHealthCard);
    when(dbService.getLastTenHealthOperationsCash()).thenReturn(mockLastTenHealthCash);
    String viewName = mainPageController.getHealthPage(model);
    assertEquals("health", viewName);
    verify(model).addAttribute(eq("health_mod_attribute"), any(HealthModelWeb.class));
    verify(model).addAttribute(eq("healthTypeList"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute(eq("healthLastTenCardOperations"),any(List.class));
    verify(model).addAttribute(eq("healthLastTenCashOperations"),any(List.class));
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
    List<LastTenTelecomOperationsModel> mockLastTenTelecomOperations = List.of(new LastTenTelecomOperationsModel(),new LastTenTelecomOperationsModel());
    when(dbService.getLastTenTelecomOperations()).thenReturn(mockLastTenTelecomOperations);
    String viewName = mainPageController.getTelecomPage(model);
    assertEquals("telecom", viewName);
    verify(model).addAttribute(eq("telecom_mod_attribute"), any(TelecomModelWeb.class));
    verify(model).addAttribute(eq("telecomOperations"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("telecomLastTen", mockLastTenTelecomOperations);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetTravelPage() {
    List<LastTenTravelOperationsModel> mockLastTenTravelOperations = List.of(new LastTenTravelOperationsModel(), new LastTenTravelOperationsModel());
    when(dbService.getLastTenTravelOperations()).thenReturn(mockLastTenTravelOperations);
    String viewName = mainPageController.getTravelPage(model);
    assertEquals("travel", viewName);
    verify(model).addAttribute(eq("travel_mod_attribute"), any(TravelModelWeb.class));
    verify(model).addAttribute(eq("travelActivities"), any(List.class));
    verify(model).addAttribute(eq("currencyNames"),any(List.class));
    verify(model).addAttribute(eq("transactionTypes"),any(List.class));
    verify(model).addAttribute("travelLastTen", mockLastTenTravelOperations);
    verifyNoMoreInteractions(model);
  }

  @Test
  void testGetAdditionalFunctionPage() {
    String viewName = mainPageController.getAdditionalFunctionsPage(model);
    assertEquals("additional", viewName);
  }
}
