package basic.conditional;

/* 점수를 입력받아 점수에 해당하는 등급을 리턴 */

public class Conditional_12 {
    public char convertScoreToGrade(int score) {
        char grade;
        if (score > 100 || score < 0)
            return 'X';

        if (score >= 90)
            grade = 'A';
         else if (score >= 80)
            grade = 'B';
         else if (score >= 70)
            grade = 'C';
         else if (score >= 60)
            grade = 'D';
         else
            grade = 'F';

        return grade;
    }
}
