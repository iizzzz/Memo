package basic.math;

// interestRate = 이자율, principal = 원금
// 연이율을 입력받아 원금이 2배 이상이 될때까지 걸리는 시간(년) 리턴

public class InterestRate {

    public int whenDouble(double interestRate) {

        // 1. 현재 이자율 = 1 + 이자율 / 100;
        double rate = 1 + interestRate / 100;
        double principal = 1;
        int year = 0;

        // 2. 원금이 2배가 되기 전까지 반복
        while (principal < 2) {
            // 3. 원금 = 원금 * 현재 이자율
            principal = principal * rate;
            year++;
        }
        return year;
    }
}
