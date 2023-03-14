package basic.conditional;

/*
두 개 수를 입력받아 아래의 2가지 조건을 모두 만족하는지 검사합니다.

EitherEven: 적어도 하나의 수는 짝수이다.
LessThan9: 두 수 모두 9보다 작다.
 */

public class Conditional_11 {
    public boolean isEitherEvenAndLessThan9(int num1, int num2) {
        if ((num1 % 2 == 0 || num2 % 2 == 0) && (num1 < 9 && num2 < 9)) {
            return true;
        }
        return false;
    }
}
