import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TextCalculatorTest {

    @DisplayName("빈문자열인 경우 0을 리턴한다.")
    @Test
    void add1() {
        String textNumbers="";
        TextCalculator textCalculator = new TextCalculator();
        assertThat(textCalculator.add(textNumbers)).isEqualTo(0);
    }

    @DisplayName("문자열로 정수를 입력받아 계산한다.")
    @CsvSource(value = {"1:1","1,2:3","0,7:7","1,2,3,4,5:15"},delimiter = ':')
    @ParameterizedTest
    void add2(String textNumbers, int expectedSum) {
        TextCalculator textCalculator = new TextCalculator();
        assertThat(textCalculator.add(textNumbers)).isEqualTo(expectedSum);
    }

    @DisplayName("정수가 아닌 경우 오류")
    @CsvSource(value = {"1.0:1","0.1,2:3","하나,7:8"},delimiter = ':')
    @ParameterizedTest
    void add_exception2(String textNumbers, int expectedSum) {
        TextCalculator textCalculator = new TextCalculator();
        assertThatThrownBy(()->textCalculator.add(textNumbers))
            .isInstanceOf(NumberFormatException.class);
    }

}
