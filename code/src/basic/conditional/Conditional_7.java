package basic.conditional;

/* 수를 입력받아 3 & 5로 각각 나눈 뒤 나머지 값에 따라 알맞은 메시지 리턴 */

public class Conditional_7 {
    public String fizzBuzz(int num) {
        if (num % 3 == 0 && num % 5 == 0)
            return "FizzBuzz";
        else if (num % 3 == 0)
            return "Fizz";
        else if (num % 5 == 0)
            return "Buzz";
        else
            return "No FizzBuzz";
    }
}
