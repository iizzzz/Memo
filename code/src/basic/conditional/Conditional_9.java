package basic.conditional;

/* 특정 달(month)을 입력받아 각 달에 몇 일이 있는지 리턴 */

public class Conditional_9 {
    public int daysInMonth(int month) {
        if (month == 2)
            return 28;
        else if (month == 4 || month == 6 || month ==9 || month == 11)
            return 30;
        else
            return 31;

        /*
        switch (month) {
            case 2: return 28;
            case 4: case 6: case 9: case 11: return 30;
            default: return 31;
         */
    }
}
