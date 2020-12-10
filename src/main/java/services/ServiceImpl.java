package services;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import dao.CardDAO;
import dao.CardDAOImpl;
import models.Account;
import models.Card;

import java.util.List;

public class ServiceImpl implements Service<Account, Card, Float> {
    private AccountDAO accountDAO = new AccountDAOImpl();
    private CardDAO cardDAO = new CardDAOImpl();

    @Override
    public void createNewCard(Account account) {
        Long num = cardDAO.findAll().size() + 1L;
        cardDAO.save(new Card(num, account.getAccountId(), account.getClientId(), num.toString()));
    }

    @Override
    public List<Card> getAllCards(Account account) {
        return cardDAO.findAllById(account.getAccountId());
    }

    @Override
    public void deposit(Account account, Float deposit) {
        account.setBalance(account.getBalance() + deposit);
        accountDAO.update(account);
    }

    @Override
    public Float getBalance(Account account) {
        return accountDAO.findById(account.getAccountId()).getBalance();
    }
}
