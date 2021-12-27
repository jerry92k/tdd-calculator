import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TextCalculatorTest {

    @Test
    void add(){
        String numbers="123";
        TextCalculator textCalculator = new TextCalculator();
        assertThat(textCalculator.add(numbers)).isEqualTo(0);
    }
}
