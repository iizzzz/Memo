package basic.math;

// 문자열을 입력받아 문자열을 구성하는 각 단어의 첫글자가 대문자인 문자열 리턴

public class LetterCapitalize {
    public String letterCapitalize(String str) {

        String[] words = str.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {

            if (words[i].isEmpty()) {
                words[i] = words[i];
            } else {
                // valueof = 스트링으로 변환
                // toUpperCase = 대문자로 변환
                // subString = 문자열 자르기
                words[i] = String.valueOf(words[i].charAt(0)).toUpperCase()
                        + words[i].substring(1);
            }
        }
        result = String.join(" ", words);
        return result;
    }
}