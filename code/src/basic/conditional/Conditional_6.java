package basic.conditional;

/* 수를 입력받아 입력된 수가 10보다 크고 짝수인지 여부 리턴 */

public class Conditional_6 {
    public boolean isEvenAndGreaterThanTen(int num) {
        if (num % 2 == 0 && num > 10) return true;
        return false;
    }
}
