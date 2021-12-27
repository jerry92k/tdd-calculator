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
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThat(textCalculator.add(textNumbers)).isEqualTo(0);
    }

    @DisplayName("문자열로 정수를 입력받아 계산한다.")
    @CsvSource(value = {"1:1","1,2:3","0,7:7","1,2,3,4,5:15"},delimiter = ':')
    @ParameterizedTest
    void add2(String textNumbers, int expectedSum) {
        TextCalculator textCalculator =new TextCalculator(new TextParser());
        assertThat(textCalculator.add(textNumbers)).isEqualTo(expectedSum);
    }

    @DisplayName("문자열 구분자로 , 와 \n을 사용한다.")
    @Test
    void add3() {
        String textNumbers="1,2\n4";
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThat(textCalculator.add(textNumbers)).isEqualTo(7);
    }

    @DisplayName("문자열 구분자로 , 와 \n을 붙여 사용해도 가능하다.")
    @Test
    void add4() {
        String textNumbers="1,2,\n4";
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThat(textCalculator.add(textNumbers)).isEqualTo(7);
    }

    @DisplayName("커스텀 구분자를 사용할 수 있다.")
    @Test
    void add5() {
        String textNumbers="//#\n1,2#,4\n3";
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThat(textCalculator.add(textNumbers)).isEqualTo(10);
    }

    @DisplayName("100 보다 큰 수는 무시한다")
    @Test
    void add6() {
        String textNumbers="//#\n1,2#,4\n3#101";
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThat(textCalculator.add(textNumbers)).isEqualTo(10);
    }

    @DisplayName("정수가 아닌 경우 오류")
    @CsvSource(value = {"1.0:1","0.1,2:3","하나,7:8"},delimiter = ':')
    @ParameterizedTest
    void add_exception1(String textNumbers, int expectedSum) {
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThatThrownBy(()->textCalculator.add(textNumbers))
            .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("음수인 경우 오류")
    @Test
    void add_exception2() {
        String textNumbers="//#\n1,2#,-14\n3,-1#-5";
        TextCalculator textCalculator = new TextCalculator(new TextParser());
        assertThatThrownBy(()->textCalculator.add(textNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("음수는 허용되지 않음")
            .hasMessageContaining("-14,-1,-5");
    }
}
