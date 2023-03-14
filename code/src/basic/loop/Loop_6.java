package basic.loop;

/* 수(num)를 입력받아 1부터 num까지의 정수로 구성된 문자열을 리턴 */

public class Loop_6 {
    public String makeDigits2(int num) {
        String result = "1";

        int index = 2;
        while(index <= num) {
            result = result + "-" + index;
            index++;
        }

        return result;
    }
}
