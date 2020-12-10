package models;

import java.util.Objects;

public class Card {
    private Long cardId;
    private Long accountId;
    private Long clientId;
    private String cardNumber;

    public Card(Long cardId, Long accountId, Long clientId, String cardNumber) {
        this.cardId = cardId;
        this.accountId = accountId;
        this.clientId = clientId;
        this.cardNumber = cardNumber;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId.equals(card.cardId) &&
                accountId.equals(card.accountId) &&
                clientId.equals(card.clientId) &&
                cardNumber.equals(card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, accountId, clientId, cardNumber);
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_id=" + cardId +
                ", account_id=" + accountId +
                ", client_id=" + clientId +
                ", card_number='" + cardNumber + '\'' +
                '}';
    }
}
