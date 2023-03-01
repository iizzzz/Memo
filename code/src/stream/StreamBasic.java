package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamBasic {
    public static void main(String[] args) {
        String[] strArr = { "aaa", "ccc", "bbb" };
        List<String> strList = Arrays.asList(strArr);

        Stream<String> stream1 = Arrays.stream(strArr);
        Stream<String> stream2 = strList.stream().sorted();

        // List로부터 스트림 생성
        List<Integer> list = Arrays.asList(1,2,3,4,5); // 가변인자
        Stream<Integer> intStream = list.stream(); // list를 소스로 컬렉션 생성
    }
}
