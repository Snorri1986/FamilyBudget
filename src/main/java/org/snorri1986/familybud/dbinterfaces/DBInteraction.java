package org.snorri1986.familybud.dbinterfaces;

import org.snorri1986.familybud.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBInteraction {
  void insertIncome(IncomeModel income);
  void insertEntertainment(EntertainmentModel entertainmentModel);
  void insertGroceries(GroceriesModel groceriesModel);
  void insertHealth(HealthModel healthModel);
  void insertRentHousing(RentHousingModel rentHousingModel);
  void insertTelecom(TelecomModel telecomModel);
  void insertTravel(TravelModel travelModel);
}
