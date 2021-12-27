import java.util.Arrays;

public class TextCalculator {

    private static final String DELIMITERS = ",|\n";

    public int add(String textNumbers) {
        String[] splited = textNumbers.split(DELIMITERS);
        return calculateTextNumbers(splited);
    }

    private int calculateTextNumbers(String[] splited) {
        return Arrays.stream(splited)
            .mapToInt(it->it.isEmpty() ? 0 : Integer.parseInt(it))
            .sum();
    }
}
