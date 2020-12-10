package dao;

import models.Account;
import utilities.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private String SQL_FIND_BY_ID = "select * from Account where Account_id = ?";
    private String SQL_UPDATE = "UPDATE Account SET Client_id = ?, Account_number = ?, Balance = ? where Account_id = ?";
    private String SQL_SAVE = "INSERT INTO Account (Client_id, Account_number, Balance) VALUES (?, ?, ?)";
    private String SQL_DELETE = "DELETE FROM Account WHERE Account_id = ?";
    private static String SQL_FIND_ALL = "select * from Account";

    @Override
    public Account findById(Long id) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)){
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    Long accountId = resultSet.getLong("Account_id");
                    Long clientId = resultSet.getLong("Client_id");
                    String accountNumber = resultSet.getString("Account_number");
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
        LinkedList<Account> accounts = new LinkedList<>();
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long accountId = resultSet.getLong("Account_id");
                    Long clientId = resultSet.getLong("Client_id");
                    String accountNumber = resultSet.getString("Account_number");
                    float balance = resultSet.getFloat("Balance");
                    accounts.add(new Account(accountId, clientId, accountNumber, balance));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void save(Account account) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_SAVE)){
            statement.setLong(1, account.getClientId());
            statement.setString(2, account.getAccountNumber());
            statement.setFloat(3, account.getBalance());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)){
            statement.setLong(1, account.getClientId());
            statement.setString(2, account.getAccountNumber());
            statement.setFloat(3, account.getBalance());
            statement.setLong(4, account.getAccountId());
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
