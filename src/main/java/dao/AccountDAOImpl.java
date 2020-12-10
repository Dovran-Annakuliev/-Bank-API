package dao;

import models.Account;
import utilities.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private String SQL_SELECT_BY_ID = "select * from Account where Account_id = ?";
    private String SQL_UPDATE = "UPDATE Account SET Client_id = ?, Account_number = ?, Balance = ? where Account_id = ?";
    private String SQL_SAVE = "INSERT INTO Account (Client_id, Account_number, Balance) VALUES (?, ?, ?)";
    private String SQL_DELETE_BY_ID = "DELETE FROM Account WHERE Account_id = ?";

    @Override
    public Account findById(Long id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    Long accountId = resultSet.getLong("Account_id");
                    Long clientId = resultSet.getLong("Client_id");
                    String accountNumber = resultSet.getString("Account_umber");
                    float balance = resultSet.getFloat("Balance");
                    return new Account(accountId, clientId, accountNumber, balance);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void save(Account account) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SAVE)){
            statement.setLong(1, account.getClientId());
            statement.setString(1, account.getAccount_number());
            statement.setFloat(1, account.getBalance());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)){
            statement.setLong(1, account.getClientId());
            statement.setString(1, account.getAccount_number());
            statement.setFloat(1, account.getBalance());
            statement.setLong(2, account.getAccountId());
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
