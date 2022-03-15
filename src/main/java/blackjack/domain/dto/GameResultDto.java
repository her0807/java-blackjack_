package blackjack.domain.dto;

import blackjack.domain.GameResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResultDto {
    private String name;
    private String result;

    private GameResultDto(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public static GameResultDto ofDealer(String name, List<GameResult> gameResults) {
        return new GameResultDto(name, toDealerResultString(gameResults));
    }

    public static GameResultDto ofPlayer(String name, GameResult gameResult) {
        return new GameResultDto(name, gameResult.getResult());
    }

    public static String toDealerResultString(List<GameResult> dealerResults) {
        Map<GameResult, Integer> result = GameResult.toGameResultMap(dealerResults);
        return result.keySet().stream()
                .filter(key -> result.get(key) != 0)
                .map(key -> result.get(key) + key.getResult())
                .collect(Collectors.joining(" "));
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
}