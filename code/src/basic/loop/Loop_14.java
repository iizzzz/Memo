package basic.loop;

/* 문자열을 입력받아 각 문자(letter) 뒤에 해당 문자의 인덱스가 추가된 문자열을 리턴 */

public class Loop_14 {
    public String characterAndNumber(String word) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            result = result + word.charAt(i) + i;
        }
        return result;
    }
}
