package org.snorri1986.familybud.dbinterfaces;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.snorri1986.familybud.models.*;
import org.springframework.stereotype.Repository;

@Repository
public class DBInteractionImpl implements DBInteraction {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void insertIncome(IncomeModel income) {

  }

  @Override
  public void insertEntertainment(EntertainmentModel entertainmentModel) {

  }

  @Override
  public void insertGroceries(GroceriesModel groceriesModel) {

  }

  @Override
  public void insertHealth(HealthModel healthModel) {

  }

  @Override
  public void insertRentHousing(RentHousingModel rentHousingModel) {

  }

  @Override
  public void insertTelecom(TelecomModel telecomModel) {

  }

  @Override
  public void insertTravel(TravelModel travelModel) {

  }
}
