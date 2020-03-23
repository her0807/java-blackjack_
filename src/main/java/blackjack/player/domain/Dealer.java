package blackjack.player.domain;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

public class Dealer extends Player {

	public static final int HIT_VALUE = 16;
	private static final String DEALER_NAME = "딜러";

	public Dealer(CardBundle cardBundle) {
		super(cardBundle, DEALER_NAME);
	}

	@Override
	public boolean isHit() {
		return cardBundle.calculateScore() <= HIT_VALUE;
	}

	public GameReport createReport(Gambler gambler) {
		return new GameReport(gambler, this.cardBundle.calculateWinOrLose(gambler.cardBundle));
	}

	public Card getFirstCard() {
		return cardBundle.getFirstCard();
	}
}
