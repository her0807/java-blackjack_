package blackjack.domain;

import static java.util.stream.Collectors.toUnmodifiableSet;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Card {

    static {
        VALUES = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Suit.values())
                .map(suit -> new Card(denomination, suit)))
            .collect(toUnmodifiableSet());
    }

    public static final Set<Card> VALUES;

    private final Denomination denomination;
    private final Suit suit;

    private Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card of(Denomination denomination, Suit suit) {
        return VALUES.stream()
            .filter(card -> card.hasSame(denomination, suit))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    private boolean hasSame(Denomination denomination, Suit suit) {
        return this.denomination == denomination && this.suit == suit;
    }

    public int addScore(int totalScore) {
        return denomination.addScore(totalScore);
    }

    public boolean isAceCard() {
        boolean ace = denomination.isAce();
        return ace;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
