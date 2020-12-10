package dao;

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

import static org.junit.jupiter.api.Assertions.*;

class CardDAOImplTest {
    private static ClientDAOImpl clientDAO = new ClientDAOImpl();
    private static AccountDAOImpl accountDAO = new AccountDAOImpl();
    private static CardDAOImpl cardDAO = new CardDAOImpl();

    @BeforeEach
    public void reset() {
        try (Reader reader = new FileReader("src/test/resources/schema.sql");
             Connection connection = DataSource.getConnection()) {
            RunScript.execute(connection, reader);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        clientDAO.save(new Client(1L, "test1"));
        clientDAO.save(new Client(2L, "test2"));
        clientDAO.save(new Client(3L, "test3"));
        accountDAO.save(new Account(1L, 1L, "1", 1));
        accountDAO.save(new Account(2L, 2L, "2", 2));
        accountDAO.save(new Account(3L, 3L, "3", 3));
    }

    @Test
    void findById() {
        Card card = new Card(1L, 1L, 1L, "1");
        cardDAO.save(card);
        assertEquals(card, cardDAO.findById(1L));
    }

    @Test
    void findAllById() {
        LinkedList<Card> list = new LinkedList<>();
        list.add(new Card(1L, 1L, 1L, "1"));
        list.add(new Card(2L, 1L, 1L, "2"));
        list.add(new Card(3L, 1L, 1L, "3"));
        for (Card c : list)
            cardDAO.save(c);
        assertEquals(list, cardDAO.findAllById(1L));
    }

    @Test
    void findAll() {
        LinkedList<Card> list = new LinkedList<>();
        list.add(new Card(1L, 1L, 1L, "1"));
        list.add(new Card(2L, 2L, 2L, "2"));
        list.add(new Card(3L, 3L, 3L, "3"));
        for (Card c : list)
            cardDAO.save(c);
        assertEquals(list, cardDAO.findAll());
    }

    @Test
    void save() {
        Card card = new Card(1L, 1L, 1L, "1");
        cardDAO.save(card);
        assertEquals(card, cardDAO.findById(1L));
    }

    @Test
    void update() {
        Card card = new Card(1L, 1L, 1L, "1");
        cardDAO.save(card);
        card.setCardNumber("10");
        cardDAO.update(card);
        assertEquals(card, cardDAO.findById(1L));
    }

    @Test
    void delete() {
        Card card = new Card(1L, 1L, 1L, "1");
        cardDAO.save(card);
        cardDAO.delete(1L);
        assertEquals(0, cardDAO.findAll().size());
    }
}