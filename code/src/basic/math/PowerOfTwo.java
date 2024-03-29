package basic.math;

// 수를 입력받아 2의 거듭제곱인지 여부 boolean 타입으로 리턴

public class PowerOfTwo {

    public boolean powerOfTwo(long num) {

        // 2의 0승 = true (2의 0승은 1이다)
        if (num == 1) return true;
        // 2의 거듭제곱이 아니면 false 리턴 (입력값의 나머지가 0이 아닐때)
        if (num % 2 != 0) return false;

        long a = 2;

        // a가 입력값보다 작을때 a*2 반복
        while (a < num) {
            a = a * 2;
        }
        // a가 입력값과 같아지면 2의 거듭제곱이니 true 리턴
        return a == num;
    }
}
