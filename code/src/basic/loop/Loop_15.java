package basic.loop;

/* 밑(base)과 지수(exponent)를 입력받아 밑의 거듭제곱을 리턴 */

public class Loop_15 {
    public int computePower(int base, int exponent) {
        int result = 1;

        for (int i = 0; i < exponent; i++) {
            result = result * base;
        }

        return result;
    }
}
