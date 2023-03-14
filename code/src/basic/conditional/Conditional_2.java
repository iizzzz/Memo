package basic.conditional;

/* 온도를 입력받아 해당 온도가 30이 넘는지 확인 후 알맞은 값을 리턴 */

public class Conditional_2 {
    public static String isGreaterThan30(int temp) {
        if (temp < 30) return "여름이 매우 덥네요";
        return "에어컨을 켜야겠다!";
    }

    public static void main(String[] args) {
        String output = isGreaterThan30(29);
        System.out.println(output);
    }
}
