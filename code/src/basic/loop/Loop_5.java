package basic.loop;

/* 수(num)를 입력받아 1부터 num까지의 정수로 구성된 문자열을 리턴 */

public class Loop_5 {
    public String makeDigits(int num) {
        String result = "";

        for(int i=1; i<=num; i++)
            result = result + i;

        return result;
    }
}
