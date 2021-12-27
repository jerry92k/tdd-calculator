import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> numbers = convertToNumbers(splited);
        validateNoMinusValue(numbers);
        return calculateSum(numbers);
    }

    private int calculateSum(List<Integer> numbers) {
        return numbers.stream()
            .mapToInt(number -> number > 100 ? 0 : number)
            .sum();
    }

    private List<Integer> convertToNumbers(List<String> splited) {
        return splited.stream()
            .map(it -> it.isEmpty() ? 0 : Integer.parseInt(it))
            .collect(Collectors.toList());
    }

    private void validateNoMinusValue(List<Integer> numbers) {
        List<Integer> minusValues = filterMinusValues(numbers);

        if (minusValues.size() > 0) {
            String minusInputs = concatValues(minusValues);
            throw new IllegalArgumentException("음수는 허용되지 않음: " + minusInputs);
        }
    }

    private List<Integer> filterMinusValues(List<Integer> numbers) {
        return numbers.stream()
            .filter(number -> number < 0)
            .collect(Collectors.toList());
    }

    private String concatValues(List<Integer> minusValues) {
        return minusValues.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(","));
    }
}
