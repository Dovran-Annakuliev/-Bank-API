package models;

import java.util.Objects;

public class Card {
    private long card_number;
    private Client client;
    private Account account;

    public Card(int card_number, Client client, Account account) {
        this.card_number = card_number;
        this.client = client;
        this.account = account;
    }

    public long getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getCard_number() == card.getCard_number() &&
                getClient().equals(card.getClient()) &&
                getAccount().equals(card.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCard_number(), getClient(), getAccount());
    }

    @Override
    public String toString() {
        return "models.Card{" +
                "card_number=" + card_number +
                ", client=" + client +
                ", account=" + account +
                '}';
    }
}
