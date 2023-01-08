```java

@Configuration
public class JavaConfiguration {

    // 1. InMemory Member Service를 Bean에 등록

    @Bean
    public CustomMemberService inMemoryMemberService(UserDetailsManager userDetailsManager,
                                                     PasswordEncoder passwordEncoder) {
        return new InMemoryMemberService(userDetailsManager, passwordEncoder);
    }

    // 2. DB Member Service를 Bean에 등록
    
    @Bean
    public CustomMemberService dbMemberService(MemberRepository memberRepository,
                                               PasswordEncoder passwordEncoder) {
        return new DBMemberService(memberRepository, passwordEncoder);
    }
}

```