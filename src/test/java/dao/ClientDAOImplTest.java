package dao;

import models.Client;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import utilities.DataSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOImplTest {
    private static ClientDAOImpl clientDAO = new ClientDAOImpl();

    @BeforeEach
    public void reset() {
        try (Reader reader = new FileReader("src/test/resources/schema.sql");
                Connection connection = DataSource.getConnection()) {
            RunScript.execute(connection, reader);
        } catch (SQLException  | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findById() {
        Client client = new Client(1L, "test");
        clientDAO.save(client);
        assertEquals(client, clientDAO.findById(1L));
    }

    @Test
    void findAll() {
        LinkedList<Client> list = new LinkedList<>();
        list.add(new Client(1L, "test1"));
        list.add(new Client(2L, "test2"));
        list.add(new Client(3L, "test3"));
        for (Client c : list)
            clientDAO.save(c);
        assertEquals(list, clientDAO.findAll());
    }

    @Test
    void save() {
        Client client = new Client(1L, "test");
        clientDAO.save(client);
        assertEquals(client, clientDAO.findById(1L));
    }

    @Test
    void update() {
        Client client = new Client(1L, "test");
        clientDAO.save(client);
        client.setName("check");
        clientDAO.update(client);
        assertEquals(client, clientDAO.findById(1L));
    }

    @Test
    void delete() {
        Client client = new Client(1L, "test");
        clientDAO.save(client);
        clientDAO.delete(1L);
        assertEquals(null, clientDAO.findById(1L));
    }
}