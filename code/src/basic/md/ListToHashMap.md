## 배열을 입력받아 배열의 첫요소와 마지막 요소를 키와 값으로 하는 HashMap 리턴

```java
public class Hash {
    public HashMap<String, String> transform(String[] arr) {

        HashMap<String, String> a = new HashMap<>();
        a.put(arr[0], arr[arr.length - 1]);
        return a;
    }
}
```
