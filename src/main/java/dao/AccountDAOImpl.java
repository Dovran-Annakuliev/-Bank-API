package dao;

import models.Account;

import javax.sql.DataSource;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private DataSource ds;
    static {
        ds =
    }
    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void save(Account entity) {

    }

    @Override
    public void update(Account entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
