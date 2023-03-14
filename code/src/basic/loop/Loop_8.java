package basic.loop;

/* 수를 입력받아 1부터 해당 수까지의 수 중에서 3의 배수로만 구성된 문자열을 리턴 */

public class Loop_8 {
    public String makeMultiplesOfDigit(int num) {
        String result = "";

        for (int i = 3; i <= num; i += 3) {
            result = result + i;
        }

        return result;
    }
}
