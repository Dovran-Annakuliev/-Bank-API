package dao;

import models.Client;
import utilities.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private String SQL_SELECT_BY_ID = "select * from Client where Client_id = ?";
    private String SQL_UPDATE = "UPDATE Client SET name = ? where Client_id = ?";
    private String SQL_SAVE = "INSERT INTO Client (name) VALUES (?)";
    private String SQL_DELETE_BY_ID = "DELETE FROM Client WHERE Client_id = ?";

    @Override
    public Client findById(Long id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    Long clientId = resultSet.getLong("Client_id");
                    String name = resultSet.getString("Name");
                    return new Client(clientId, name);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public void save(Client client) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SAVE)){
            statement.setString(1, client.getName());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)){
            statement.setString(1, client.getName());
            statement.setLong(2, client.getId());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
