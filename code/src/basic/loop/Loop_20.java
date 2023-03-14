package basic.loop;

/* 문자열을 입력받아 해당 문자열에 중복된 문자(letter)가 존재하는지 여부를 리턴 */

public class Loop_20 {
    public boolean hasRepeatedCharacter(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
