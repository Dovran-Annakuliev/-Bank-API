package dao;

import models.Account;
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

class AccountDAOImplTest {
    private static ClientDAOImpl clientDAO = new ClientDAOImpl();
    private static AccountDAOImpl accountDAO = new AccountDAOImpl();

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
    }

    @Test
    void findById() {
        Account account = new Account(1L, 1L, "1", 1);
        accountDAO.save(account);
        assertEquals(account, accountDAO.findById(1L));
    }

    @Test
    void findAll() {
        LinkedList<Account> list = new LinkedList<>();
        list.add(new Account(1L, 1L, "1", 1));
        list.add(new Account(2L, 2L, "2", 2));
        list.add(new Account(3L, 3L, "3", 3));
        for (Account a : list)
            accountDAO.save(a);
        assertEquals(list, accountDAO.findAll());
    }

    @Test
    void save() {
        Account account = new Account(1L, 1L, "1", 1);
        accountDAO.save(account);
        assertEquals(account, accountDAO.findById(1L));
    }

    @Test
    void update() {
        Account account = new Account(1L, 1L, "1", 1);
        accountDAO.save(account);
        account.setAccountNumber("5");
        account.setBalance(10);
        accountDAO.update(account);
        assertEquals(account, accountDAO.findById(1L));
    }

    @Test
    void delete() {
        Account account = new Account(1L, 1L, "1", 1);
        accountDAO.save(account);
        accountDAO.delete(1L);
        assertEquals(0, accountDAO.findAll().size());
    }
}