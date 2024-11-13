package org.snorri1986.familybud.dbinterfaces;

import org.snorri1986.familybud.models.IncomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBInteractionService {

  @Autowired
  private DBInteraction dbRepository;

    public void pushIncome(IncomeModel incomeModel) {
      dbRepository.insertIncome(incomeModel);
    }
}
