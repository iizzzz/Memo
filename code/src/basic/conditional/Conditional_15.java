package basic.conditional;

/* 두 개의 boolean 값을 입력받아 or 연산을 적용한 것과 같은 결과를 리턴 */

public class Conditional_15 {

    public boolean or(boolean expression1, boolean expression2) {
        if (!expression1 && !expression2)
            return false;
         else
            return true;
    }
}
