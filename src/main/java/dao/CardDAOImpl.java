package dao;

import models.Account;
import models.Card;
import utilities.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CardDAOImpl implements CardDAO {
    private String SQL_FIND_BY_ID = "select * from Card where Card_id = ?";
    private String SQL_UPDATE = "UPDATE Card SET Account_id = ?, Client_id = ?, Card_number = ? where Card_id = ?";
    private String SQL_SAVE = "INSERT INTO Card (Account_id, Client_id, Card_number) VALUES (?, ?, ?)";
    private String SQL_DELETE = "DELETE FROM Card WHERE Card_id = ?";
    private String SQL_FIND_ALL = "select * from Card";
    private String SQL_FIND_ALL_BY_ID = "select * from Card WHERE Account_id = ?";

    @Override
    public Card findById(Long id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)){
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    Long CardId = resultSet.getLong("Card_id");
                    Long AccountId = resultSet.getLong("Account_id");
                    Long ClientId = resultSet.getLong("Client_id");
                    String Card_number = resultSet.getString("Card_number");
                    return new Card(CardId, AccountId, ClientId, Card_number);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public List<Card> findAllById(Long id) {
        LinkedList<Card> cards = new LinkedList<>();
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long CardId = resultSet.getLong("Card_id");
                    Long AccountId = resultSet.getLong("Account_id");
                    Long ClientId = resultSet.getLong("Client_id");
                    String Card_number = resultSet.getString("Card_number");
                    cards.add(new Card(CardId, AccountId, ClientId, Card_number));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return cards;
    }

    @Override
    public List<Card> findAll() {
        LinkedList<Card> cards = new LinkedList<>();
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long CardId = resultSet.getLong("Card_id");
                    Long AccountId = resultSet.getLong("Account_id");
                    Long ClientId = resultSet.getLong("Client_id");
                    String Card_number = resultSet.getString("Card_number");
                    cards.add(new Card(CardId, AccountId, ClientId, Card_number));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return cards;
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
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_DELETE)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
