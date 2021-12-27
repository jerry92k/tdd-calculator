import java.util.Arrays;

public class TextCalculator {

    public int add(String textNumbers) {
        if(textNumbers.isEmpty()){
            return 0;
        }
        String[] splited = textNumbers.split(",");
        if(splited.length>2){
            throw new IllegalArgumentException("정수는 0,1,2 개만 받을 수 있습니다.");
        }
        try {
            return Arrays.stream(splited)
                .mapToInt(Integer::parseInt)
                .sum();
        }catch (NumberFormatException ex){
            throw new NumberFormatException("입력형식이 잘못되었습니다.");
        }
    }
}
