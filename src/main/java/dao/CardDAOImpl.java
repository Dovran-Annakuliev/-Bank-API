package dao;

import models.Card;
import utilities.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CardDAOImpl implements CardDAO {
    private String SQL_SELECT_BY_ID = "select * from Card where Card_id = ?";
    private String SQL_UPDATE = "UPDATE Card SET Account_id = ?, Client_id = ?, Card_number = ? where Card_id = ?";
    private String SQL_SAVE = "INSERT INTO Card (Account_id, Client_id, Card_number) VALUES (?, ?, ?)";
    private String SQL_DELETE_BY_ID = "DELETE FROM Card WHERE Card_id = ?";

    @Override
    public Card findById(Long id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    Long CardId = resultSet.getLong("Card_id");
                    Long AccountId = resultSet.getLong("Card_id");
                    Long ClientId = resultSet.getLong("Card_id");
                    String Card_number = resultSet.getString("Card_number");
                    return new Card(CardId, AccountId, ClientId, Card_number);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public void save(Card card) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SAVE)){
            statement.setLong(1, card.getAccountId());
            statement.setLong(2, card.getClientId());
            statement.setString(3, card.getCardNumber());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)){
            statement.setLong(1, card.getAccountId());
            statement.setLong(2, card.getClientId());
            statement.setString(3, card.getCardNumber());
            statement.setLong(4, card.getCardId());
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
