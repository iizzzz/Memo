package basic.loop;

/* 수를 입력받아 n-factorial(n!) 값을 리턴 */

/*
factorial(n)은 자연수 1부터 n까지의 곱을 계산하는 함수입니다.
factorial(1) = 1
factorial(2) = 1 * 2 = 2
factorial(3) = 1 * 2 * 3 = 6
factorial(4) = 1 * 2 * 3 * 4 = 24
 */

public class Loop_3 {
    public int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
