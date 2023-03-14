package basic.loop;

/* 수를 입력받아 0부터 해당 수까지의 합을 리턴 */

public class Loop_1 {
    public int sumTo(int num) {
        int result = 0;
        for (int i=0; i<=num; i++) {
            result = result + i;
        }
        return result;
    }
}
