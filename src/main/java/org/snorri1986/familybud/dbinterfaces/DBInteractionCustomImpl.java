package org.snorri1986.familybud.dbinterfaces;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.snorri1986.familybud.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DBInteractionCustomImpl implements DBInteractionCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void insertIncome(IncomeModel income) {
    Query query = entityManager.createNativeQuery("SELECT public.i_income(:income_type, :amount_value, :currency_value, :oper_date, :payment_card, :comm_value)");
    query.setParameter("income_type", income.getIncomeType());
    query.setParameter("amount_value", income.getAmount());
    query.setParameter("currency_value", income.getCurrency());
    query.setParameter("oper_date", income.getTransactionDate());
    query.setParameter("payment_card", income.getCardNum());
    query.setParameter("comm_value", income.getOperDescription());
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
