package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

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

    // Verify the returned view name
    assertEquals("main", viewName);

    // Verify that the model was updated with the attribute 'theDate'
    verify(model).addAttribute(eq("theDate"), any(LocalDateTime.class));
  }
}
