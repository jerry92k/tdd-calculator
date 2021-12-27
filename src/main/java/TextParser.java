import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static final String ERROR_MESSAGE_DELMITER_FORMAT="올바르지 않은 구분자 포맷입니다.";
    private static final String BASIC_DELIMITERS = ",|\n";
    private static final int SPLIT_CUSTOM_DELIMITER_INDEX = 1;
    private static final int SINGLE_LETTER_LENGTH = 1;
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.*)\n(.*)");

    public List<String> splitText(String text) {
        Matcher m = CUSTOM_DELIMITER_PATTERN.matcher(text);
        if(!hasCustomDelimiter(m)){
            return splitWithDelimiters(text, BASIC_DELIMITERS);
        }

        String customDelimiter = extractCustomDelimiter(m);
        String parsedText = extractText(text);
        return splitWithDelimiters(parsedText, getDelimitersWithCustom(customDelimiter));
    }

    private boolean hasCustomDelimiter(Matcher m) {
        return m.find();
    }

    private String extractCustomDelimiter(Matcher m) {
        String customDelimiter = m.group(SPLIT_CUSTOM_DELIMITER_INDEX);
        if(customDelimiter.length()>SINGLE_LETTER_LENGTH) {
            validateDelimiterFormat(customDelimiter);
        }
        return customDelimiter;
    }

    private String extractText(String text) {
        int delimiterEnd = text.indexOf("\n");
        if(delimiterEnd==text.length()-1){
            return "";
        }
        return text.substring(delimiterEnd+1);
    }

    private List<String> splitWithDelimiters(String text, String delimiters) {
        if (text==null || text.isEmpty()) {
            return Arrays.asList("0");
        }
        return Arrays.asList(text.split(delimiters));
    }

    private String getDelimitersWithCustom(String customDelimiter) {
        return String.join("|", customDelimiter,BASIC_DELIMITERS);
    }

    private void validateDelimiterFormat(String customDelimiter) {
        Stack<Character> bracketStack = new Stack<>();
        for(char ch : customDelimiter.toCharArray()){
            if(ch=='['){
                bracketStack.push(ch);
            }
            else if(ch==']'){
                if(bracketStack.isEmpty()){
                    throw new IllegalArgumentException(ERROR_MESSAGE_DELMITER_FORMAT);
                }
                bracketStack.pop();
            }
            else{
                if(bracketStack.isEmpty()){
                    throw new IllegalArgumentException(ERROR_MESSAGE_DELMITER_FORMAT);
                }
            }
        }

        if(!bracketStack.isEmpty()){
            throw new IllegalArgumentException(ERROR_MESSAGE_DELMITER_FORMAT);
        }
    }
}
