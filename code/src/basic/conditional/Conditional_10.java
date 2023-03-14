package basic.conditional;

/* 요일을 입력받아 해당 요일의 다음 요일을 리턴 */

public class Conditional_10 {
    public String followingDay(String day) {
        switch (day) {
            case "월요일": return "화요일";
            case "화요일": return "수요일";
            case "수요일": return "목요일";
            case "목요일": return "금요일";
            case "금요일": return "토요일";
            case "토요일": return "일요일";
            case "일요일": return "월요일";
            default: return "올바른 요일이 아닙니다";


            /*
            if (day.equals("월요일")) {
                return "화요일";
            } else if (day.equals("화요일")) {
                return "수요일";
            } else if (day.equals("수요일")) {
                return "목요일";
            } else if (day.equals("목요일")) {
                return "금요일";
            } else if (day.equals("금요일")) {
                return "토요일";
            } else if (day.equals("토요일")) {
                return "일요일";
            } else if (day.equals("일요일")) {
                return "월요일";
            } else {
                return "올바른 요일이 아닙니다";
            }
             */
        }
    }
}
