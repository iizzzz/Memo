package basic.loop;

/* 수를 입력받아 약수(factor)의 합을 리턴 */

public class Loop_16 {
    public int getSumOfFactors(int num) {
        int result = 0;

        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                result = result + i;
            }
        }

        return result;
    }
}
