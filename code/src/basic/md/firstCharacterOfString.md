## 문자열 입력받아 각 단어의 첫글자로 이루어진 새 문자열 리턴

```java
public class First {
    public String first(String str) {

        String[] words = str.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            result += words[i].charAt(0);
        }
        return result;
    }
}
```
