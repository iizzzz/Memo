package basic.loop;

/* 수(num)를 입력받아 1을 포함하여 num개의 홀수로 구성된 문자열을 리턴 */

public class Loop_7 {
    public String makeOddDigits(int num) {
        String result = "";

        int count = 0;
        int i = 1;
        while (count < num) {
            result = result + i;
            i = i + 2;
            count++;
        }

        return result;
    }
}
