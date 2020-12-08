package models;

import java.util.Objects;

public class Account {
    private long account_number;
    private float balance;
    private Client client;

    public Account(int account_number, float balance, Client client) {
        this.account_number = account_number;
        this.balance = balance;
        this.client = client;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccount_number() == account.getAccount_number() &&
                Float.compare(account.getBalance(), getBalance()) == 0 &&
                getClient().equals(account.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccount_number(), getBalance(), getClient());
    }

    @Override
    public String toString() {
        return "models.Account{" +
                "account_number=" + account_number +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }
}
