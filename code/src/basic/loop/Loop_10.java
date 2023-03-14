package basic.loop;

/* 문자열을 입력받아 버그('#')의 인덱스를 리턴 */

public class Loop_10 {
    public int findTheBug(String word) {

        for(int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '#')
                return i;
        }

        return -1;
    }
}
