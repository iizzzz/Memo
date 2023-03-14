package basic.loop;

public class Loop_2 {
    public boolean isOdd(int num) {
        //조건문을 사용하여 짝수인 경우 return false를,
        //홀수인 경우 return true;를 작성해야 합니다.
        //TODO :

        while(true) {
            if((num & 1) == 1) return true;
            return false;
        }
    }
}
