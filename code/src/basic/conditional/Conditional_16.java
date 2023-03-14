package basic.conditional;

/*시, 분, 초를 입력받아 1초를 더한 결과값을 특정 형태의 메시지로 리턴 */

public class Conditional_16 {

    public String addOneSecond(int hour, int minute, int second) {

        if (second == 59) {
            minute += 1;
            second = 0;
        } else {
            second += 1;
        }

        if (minute == 60) {
            hour += 1;
            minute = 0;
        }

        if (hour == 24) {
            hour = 0;
        }

        return String.format("1초 뒤에 %d시 %d분 %d초 입니다", hour, minute, second);
    }
}
