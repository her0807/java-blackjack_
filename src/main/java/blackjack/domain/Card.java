package blackjack.domain;

public class Card {
    private final CardNumber cardNumber;
    private final Symbol symbol;

    public Card(CardNumber cardNumber,Symbol symbol) {
        this.cardNumber = cardNumber;
        this.symbol = symbol;
    }

    public int getCardNumberValue() {
        return cardNumber.getCardNumberValue();
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }

    @Override
    public String toString() {
        return cardNumber.toString() + symbol.toString();
    }
}