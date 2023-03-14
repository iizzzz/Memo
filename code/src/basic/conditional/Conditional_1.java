package basic.conditional;

/* 나이를 입력받아 술을 마실 수 있는지(18세 이상) 여부를 리턴 */

public class Conditional_1 {
    public static boolean isOldEnoughToDrink(int age) {
        if (age >= 18) return true;
        return false;
    }

    public static void main(String[] args) {
        boolean output = isOldEnoughToDrink(18);
        System.out.println(output);
    }
}
