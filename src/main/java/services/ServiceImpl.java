package services;

import dao.CardDAO;
import dao.CardDAOImpl;
import models.Card;

import java.util.List;

public class ServiceImpl implements Service {
    public void createNewCard(Card card) {
        CardDAO cardDAO = new CardDAOImpl();
        cardDAO.save(card);
    }

    public List getAllCards(Object o) {
        return null;
    }

    public void deposit(Object o) {

    }

    public Object getBalance() {
        return null;
    }
}
