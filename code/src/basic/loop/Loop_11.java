package basic.loop;

/* 문자열과 문자를 입력받아 문자열에서 문자(letter)가 등장하는 횟수를 리턴 */

public class Loop_11 {
    public int countCharacter(String str, char letter) {
        int counter = 0;
        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == letter)
                counter = counter + 1;
        }

        return counter;
    }
}
