package basic.loop;

/* 1 이상의 자연수를 입력받아 소수(prime number)인지 여부를 리턴 */

public class Loop_17 {
    public boolean isPrime(int num) {
        // TODO:
        int sqrt = (int)Math.sqrt(num);

        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i < sqrt; i += 2) {
            if (num %i == 0) {
                return false;
            }
        }
        return true;
    }
}
