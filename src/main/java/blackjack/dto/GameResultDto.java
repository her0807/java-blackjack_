package blackjack.dto;

import blackjack.domain.Gamer;
import java.util.Objects;

public class GameResultDto {

    private final String name;
    private final int bettingMoney;

    public GameResultDto(String gamerName, int bettingMoney) {
        this.name = gamerName;
        this.bettingMoney = bettingMoney;
    }

    public static GameResultDto from(Gamer gamer) {
        return new GameResultDto(gamer.getName(), gamer.getBettingMoney());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResultDto that = (GameResultDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
