package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {

    @DisplayName("배팅금액은 0원 이상이어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-1220"})
    void 배팅금액_음수_예외(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액 최소 금액은 0원입니다.");
    }

    @DisplayName("배팅금액은 숫자여야한다.")
    @ParameterizedTest
    @ValueSource(strings = {"오백원", " ", "1만원", ","})
    void 배팅금액_숫자_외_예외(String value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("string");
    }

    @DisplayName("배팅금액을 추가한다.")
    @Test
    void 게임_결과_얻은_금액() {
        // given
        BettingMoney battingMoney = new BettingMoney(0);
        // when
        battingMoney.addMoney(1000);
        // then
        assertThat(battingMoney.getValue()).isEqualTo(1000);
    }
}
