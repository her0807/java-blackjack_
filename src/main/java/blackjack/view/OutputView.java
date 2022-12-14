package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.dto.CardDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.GameResultsDto;
import blackjack.dto.GamerDto;
import blackjack.dto.PlayersDto;

public class OutputView {

    private static final String DELIMITER_JOINING = ", ";
    private static final String ERROR = "[ERROR] ";
    private static final String NUMBER_FORMAT_MESSAGE = "숫자를 입력하셔야 합니다.";
    private static final String STRING = "string";

    private OutputView() {
    }

    public static void printGuideMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        if (message.contains(STRING)) {
            message = NUMBER_FORMAT_MESSAGE;
        }
        System.out.println(ERROR + message);
    }

    public static void printStartInfo(GamerDto dealer, PlayersDto players) {
        String names = players.getValue().stream()
                .map(GamerDto::getName)
                .collect(joining(DELIMITER_JOINING));
        System.out.println("\n" + dealer.getName() + "와 " + names + "에게 2장씩 나누었습니다.");

        System.out.println(dealer.getName() + ": " + dealer.getCards().getValue().get(0).getValue());
        for (GamerDto player : players.getValue()) {
            printPlayerCardInfo(player);
        }
        System.out.println();
    }

    public static void printPlayerCardInfo(GamerDto player) {
        String cardsInfo = player.getCards().getValue().stream()
                .map(CardDto::getValue)
                .collect(joining(DELIMITER_JOINING));

        System.out.println(player.getName() + ": " + cardsInfo);
    }

    public static void printDealerDrawableInfo() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultInfo(GamerDto dealer, PlayersDto players) {
        System.out.println();
        createDealerResultInfo(dealer);
        for (GamerDto player : players.getValue()) {
            createPlayerResultInfo(player);
        }
    }

    private static void createPlayerResultInfo(GamerDto player) {
        String cardsInfo = player.getCards().getValue().stream()
                .map(CardDto::getValue)
                .collect(joining(DELIMITER_JOINING));

        System.out.println(player.getName() + ": " + cardsInfo + " - 결과: " + player.getCards().getTotalScore());
    }

    private static void createDealerResultInfo(GamerDto dealer) {
        String cardsInfo = dealer.getCards().getValue().stream()
                .map(CardDto::getValue)
                .collect(joining(DELIMITER_JOINING));

        System.out.println(dealer.getName() + ": " + cardsInfo + " - 결과: " + dealer.getCards().getTotalScore());
    }


    public static void printGameResult(GameResultsDto playerResults, GameResultDto dealerResult) {
        printDealerGameResult(dealerResult);
        printPlayerGameResult(playerResults);
    }

    private static void printDealerGameResult(GameResultDto result) {
        System.out.printf("\n## 최종 수익\n" + result.getName() + ": %s\n", result.getBettingMoney());
    }

    private static void printPlayerGameResult(GameResultsDto results) {
        String resultInfo = results
                .getValue()
                .stream()
                .map(player -> player.getName() + ": " + player.getBettingMoney())
                .collect(joining("\n"));

        System.out.println(resultInfo);
    }
}

