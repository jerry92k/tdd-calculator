import java.util.Arrays;

public class TextCalculator {

    public int add(String textNumbers) {
        if(textNumbers.isEmpty()){
            return 0;
        }
        String[] splited = textNumbers.split(",");
        try {
            return Arrays.stream(splited)
                .mapToInt(Integer::parseInt)
                .sum();
        }catch (NumberFormatException ex){
            throw new NumberFormatException("입력형식이 잘못되었습니다.");
        }
    }
}
