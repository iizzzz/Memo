package basic.loop;

/* 문자열을 입력받아 해당 문자열을 처음부터 한 글자(letter)씩 다시 작성하려고 합니다.
이 때, 한 글자를 추가할 때마다 부분적으로 완성된 문자열을 전부 이어붙인 문자열을 리턴 */

public class Loop_21 {
    public String makeMarginalString(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j <= i; j++) {
                result = result + str.charAt(j);
            }
        }

        return result;
    }
}
