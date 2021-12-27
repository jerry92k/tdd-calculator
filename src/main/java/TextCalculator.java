import java.util.Arrays;
import java.util.List;

public class TextCalculator {

    private TextParser textParser;

    public TextCalculator(TextParser textParser) {
        this.textParser = textParser;
    }

    public int add(String textNumbers) {
        List<String> splited = textParser.splitText(textNumbers);
        return calculateTextNumbers(splited);
    }

    private int calculateTextNumbers(List<String> splited) {
        return splited.stream()
            .mapToInt(it->it.isEmpty() ? 0 : Integer.parseInt(it))
            .sum();
    }
}
