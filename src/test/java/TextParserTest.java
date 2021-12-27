import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextParserTest {

    @DisplayName("구분자로 문자열을 파싱하여 리스트로 변환")
    @Test
    void splitText(){
        String text= "//#\n1,2,3#54\n23,#0";
        TextParser textParser = new TextParser();
        assertThat(textParser.splitText(text)).isEqualTo(Arrays.asList("1","2","3","54","23","","0"));
    }

    @DisplayName("2글자 이상 구분자를 받아들일 수 있게 변환")
    @Test
    void splitText2(){
        String text= "//[#$]\n1,2,3#$54\n23,#$0";
        TextParser textParser = new TextParser();
        assertThat(textParser.splitText(text)).isEqualTo(Arrays.asList("1","2","3","","54","23","","","0"));
    }

    @DisplayName("구분자 묶음 기호 오류")
    @Test
    void splitText_exception1(){
        String text= "//[#$]]\n1,2,3#$54\n23,#$0";
        TextParser textParser = new TextParser();
        assertThatThrownBy(()->textParser.splitText(text))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구분자 묶음 기호 이후 구분자 발생 오류")
    @Test
    void splitText_exception2(){
        String text= "//[#$]@\n1,2,3#$54\n23,#$0";
        TextParser textParser = new TextParser();
        assertThatThrownBy(()->textParser.splitText(text))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
