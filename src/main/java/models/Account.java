package models;

import java.util.Objects;

public class Account {
    private Long accountId;
    private Long clientId;
    private String account_number;
    private float balance;

    public Account(Long accountId, Long clientId, String account_number, float balance) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.account_number = account_number;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Float.compare(account.balance, balance) == 0 &&
                accountId.equals(account.accountId) &&
                clientId.equals(account.clientId) &&
                account_number.equals(account.account_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, clientId, account_number, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", clientId=" + clientId +
                ", account_number='" + account_number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
