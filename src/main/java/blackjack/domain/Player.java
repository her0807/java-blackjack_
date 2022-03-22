package blackjack.domain;

public class Player extends Participant {
    private boolean finish = false;

    private final BettingMoney bettingMoney;

    public Player(String name, int money) {
        super(name);
        this.bettingMoney = new BettingMoney(money);
    }

    public void closeTurn() {
        finish = true;
    }

    public boolean isFinished() {
        return super.getHoldingCard().isFullScoreOrBust() || finish;
    }

    public GameResult judgeResult(Dealer dealer) {
        if (onlyPlayerBlackjack(dealer)) {
            return GameResult.WIN_BLACKJACK;
        }
        if (onlyDealerBust(dealer) || bothNotBustAndPlayerTotalLarger(dealer)) {
            return GameResult.WIN;
        }
        if (onlyPlayerBust(dealer) || onlyDealerBlackjack(dealer) || bothNotBustAndDealerTotalLarger(dealer)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private boolean onlyDealerBust(Dealer dealer) {
        return dealer.isBust() && !isBust();
    }

    private boolean onlyPlayerBust(Dealer dealer) {
        return !dealer.isBust() && isBust();
    }

    private boolean onlyDealerBlackjack(Dealer dealer) {
        return !hasBlackJack() && dealer.hasBlackJack();
    }

    private boolean onlyPlayerBlackjack(Dealer dealer) {
        return hasBlackJack() && !dealer.hasBlackJack();
    }

    private boolean bothNotBustAndPlayerTotalLarger(Dealer dealer) {
        if (dealer.isBust() || isBust()) {
            return false;
        }
        return calculateTotal() > dealer.calculateTotal();
    }

    private boolean bothNotBustAndDealerTotalLarger(Dealer dealer) {
        if (dealer.isBust() || isBust()) {
            return false;
        }
        return calculateTotal() < dealer.calculateTotal();
    }

    public int getBettingMoney() {
        return bettingMoney.bettingMoney;
    }

    private static class BettingMoney {
        private final int bettingMoney;

        private BettingMoney(int initial) {
            if (initial <= 0) {
                throw new IllegalArgumentException("[ERROR] 금액은 1원 이상 입력하셔야 합니다.");
            }
            this.bettingMoney = initial;
        }
    }
}
