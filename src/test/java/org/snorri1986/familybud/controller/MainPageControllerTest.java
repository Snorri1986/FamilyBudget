package org.snorri1986.familybud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainPageController.class)
public class MainPageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetMainPage() throws Exception {
    mockMvc.perform(get("/main"))
            .andExpect(status().isOk())                       // HTTP 200 status
            .andExpect(view().name("main"))                  // Correct view name
            .andExpect(model().attributeExists("theDate"));  // Model contains 'theDate'
  }
}
