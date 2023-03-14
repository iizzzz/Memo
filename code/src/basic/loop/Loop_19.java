package basic.loop;

/* 문자열을 입력받아 해당 문자열에 등장하는 각 문자(letter)를 가지고 만들 수 있는 길이 2의 문자열들을 리턴 */

public class Loop_19 {
    public String makePermutations(String str) {
        String result = "";

        if(str.isEmpty()) {
            return "";
        }
        for (int left = 0; left < str.length(); left++) {
            for (int right = 0; right < str.length(); right++) {
                result = result + str.charAt(left) + str.charAt(right) + ",";
            }
        }

        return result.substring(0, result.length() -1);
    }
}
