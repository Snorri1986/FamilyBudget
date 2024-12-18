package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MainPageController.class)
public class MainPageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetMainPage() throws Exception {
    mockMvc.perform(get("http://localhost:8080/main"))
            .andExpect(status().isOk())
            .andExpect(content().string("FamilyBudget Cloud App"));
  }
}
