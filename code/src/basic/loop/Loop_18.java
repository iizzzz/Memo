package basic.loop;

/* 2 이상의 자연수를 입력받아 2부터 해당 수까지의 소수(prime number)들을 리턴 */

public class Loop_18 {
    public String listPrimes(int num) {
        String result = "2";
        for (int candi = 3; candi <= num; candi += 2) {
            boolean isPrime = true;
            int sqrt = (int) Math.sqrt(candi);
            for (int divider = 3; divider <= sqrt; divider += 2) {
                if (candi % divider == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                result = result + "-" + candi;
            }
        }

        return result;
    }
}
