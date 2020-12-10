package services;

import dao.AccountDAOImpl;
import dao.CardDAO;
import dao.CardDAOImpl;
import dao.ClientDAOImpl;
import models.Account;
import models.Card;
import models.Client;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.DataSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest {
    private static ServiceImpl service = new ServiceImpl();
    private static ClientDAOImpl clientDAO = new ClientDAOImpl();
    private static AccountDAOImpl accountDAO = new AccountDAOImpl();
    private CardDAO cardDAO = new CardDAOImpl();

    @BeforeEach
    public void reset() {
        try (Reader reader = new FileReader("src/test/resources/schema.sql");
             Connection connection = DataSource.getConnection()) {
            RunScript.execute(connection, reader);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        clientDAO.save(new Client(1L, "test1"));
        accountDAO.save(new Account(1L, 1L, "1", 1));

    }

    @Test
    void createNewCard() {
        service.createNewCard(accountDAO.findById(1L));
        assertEquals(1, cardDAO.findAll().size());
    }

    @Test
    void getAllCards() {
        LinkedList<Card> list = new LinkedList<>();
        list.add(new Card(1L, 1L, 1L, "1"));
        list.add(new Card(2L, 1L, 1L, "2"));
        list.add(new Card(3L, 1L, 1L, "3"));
        cardDAO.save(new Card(1L, 1L, 1L, "1"));
        cardDAO.save(new Card(2L, 1L, 1L, "2"));
        cardDAO.save(new Card(3L, 1L, 1L, "3"));
        assertEquals(list, service.getAllCards(accountDAO.findById(1L)));
    }

    @Test
    void deposit() {
        service.deposit(accountDAO.findById(1L), 9f);
        assertEquals(10, accountDAO.findById(1L).getBalance());
    }

    @Test
    void getBalance() {
        assertEquals(1, accountDAO.findById(1L).getBalance());
    }
}