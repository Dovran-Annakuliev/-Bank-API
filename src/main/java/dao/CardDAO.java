package dao;

import models.Account;
import models.Card;

import java.util.List;

public interface CardDAO extends CrudDAO<Card, Long> {
    public List<Card> findAllById(Long id);
}
