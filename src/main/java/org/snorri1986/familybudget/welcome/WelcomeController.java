package org.snorri1986.familybudget.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

  @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
      return "Welcome!";
    }
  }

