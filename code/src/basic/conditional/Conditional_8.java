package basic.conditional;

/* 두개의 수와 기호를 입력받아 알맞게 계산한 값 리턴 */

public class Conditional_8 {
    public int miniCalculator(int num1, int num2, char operator) {
        switch (operator) {
            case ('+'):
                return num1 + num2;
            case ('-'):
                return num1 - num2;
            case ('*'):
                return num1 * num2;
            case ('/'):
                return num1 / num2;
            case ('%'):
                return num1 % num2;
            default:
                return 0;
        }
    }
}
