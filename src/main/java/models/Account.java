package models;

import java.util.Objects;

public class Account {
    private Long accountId;
    private Long clientId;
    private String accountNumber;
    private float balance;

    public Account(Long accountId, Long clientId, String accountNumber, float balance) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
                accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, clientId, accountNumber, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", clientId=" + clientId +
                ", account_number='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
