## 문자열을 입력받아 순서가 뒤집힌 문자열 리턴

## String 인스턴스는 생성 되면 read-only지만 buffer는 rw다

### Method
append() = 문자열 추가
capacity() = 버퍼크기 반환 (default 16 + 문자열의 길이 리턴)
delete(1,6) = 범위 삭제
deleteCharAt(1) = 문자 삭제
insert(4, "abc") = 문자열 삽입
reverse() = 순서 반전

public String reverse(String str) {
    String a = new StringBuffer(str).reverse().toString();
    return a;
}