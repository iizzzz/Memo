package basic.loop;

/* 두 개의 수를 입력받아 두 수를 포함해 두 수 사이의 수 중 2의 배수의 개수를 리턴 */

public class Loop_9 {
    public int makeMultiplesOfDigit2(int num1, int num2) {
        int count = 0;
        int start = num1;
        int end = num2;

        if (num1 > num2) {
            start = num2;
            end = num1;
        }

        if (start == 0) {
            start = 1;
        }

        for (int i = start; i <= end; i++)
            if (i % 2 == 0)
                count += 1;

        return count;
    }
}
