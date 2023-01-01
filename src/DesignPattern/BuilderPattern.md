```java

/* -------------- Builder Pattern 사용 X -------------- */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private String adress;
    private int age;
    
    /* Entity의 필드 중 adress가 없는 객체 생성을 하기 위한 생성자 & 정적 팩토리 메서드 */
    
    public User (String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public static User of(String name, int age) {
        return new User(name, age);
    }
}

/* -------------- Builder Pattern 사용 -------------- */

@Data
@Builder
@AllArgsConstructor
public class User {
    private String name;
    private String adress;
    private int age;
}

public class UserService {
    
    // 1. 빌더 패턴을 이용한 객체 생성
    public User create() {
        User user = User.builder()
                .name("abc")
                .adress("abc")
                .age(25)
                .build();
    }
    
    // 2. Entity의 필드를 추가 해야 할때 객체의 유연한 변경 (email 필드를 추가하는걸로 가정)
    public User create() {
        User user = User.builder()
                .name("abc")
                .adress("abc")
                .email("abc@abc.com")
                .age(25)
                .build();
    }
}

```