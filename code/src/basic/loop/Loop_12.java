package basic.loop;

/* 숫자 문자열을 입력받아 문자열을 구성하는 각 숫자 중 가장 큰 수를 나타내는 숫자를 리턴 */

public class Loop_12 {
    public int getMaxNumberFromString(String str) {
        int maxNum = 0;
        int length = str.length();

        for (int i = 0; i < length; i++) {
            char character = str.charAt(i);
            int num = Character.getNumericValue(character);

            if (num > maxNum) {
                maxNum = num;
            }
        }

        return maxNum;
    }
}
