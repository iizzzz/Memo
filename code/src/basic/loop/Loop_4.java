package basic.loop;

/* 문자열과 수를 입력받아 반복된 문자열을 리턴 */

public class Loop_4 {
    public String repeatString(String str, int num) {
        String result = "";

        if(str.isEmpty())
            return "";
        else if(num == 0)
            return "";

        for(int i = 0; i < num; i++)
            result = result + str;

        return result;

        /*
        String result = "";

        if ("".equals(str)) return "";

        for (int i=1; i<=num; i++)
            result = result + str;
         return result;
         */
    }
}
