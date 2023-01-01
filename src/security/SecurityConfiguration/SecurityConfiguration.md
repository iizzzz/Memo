```java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    // Spring Security의 설정 정보 작성

    @Bean
    public UserDetailsManager user() {

        /** 이 방식은 데모 & 테스트 시 유용하게 사용할 수 있으며, 실무에서 사용 X 
         *  사용자 인증을 위한 계정정보를 메모리에 고정
         *
         * 1. UserDetails를 관리하는 UserDetailsManager 인터페이스 타입 선언
         * 2. UserDetails 인터페이스의 구현체인 User 클래스를 사용하여 User의 인증 정보 생성
         * 3. withDefaultPasswordEncoder를 사용한 패스워드의 암호화 적용
         * 4. roles() - 유저의 역할 지정
         * 5. 메모리상으로 UserDetails를 관리하므로 InMemoryUserDetailsManager 구현체를 사용하여 객체를 빈으로 등록
         */

        UserDetails user =
                User.withDefaultPasswordEncoder() // 패스워드 암호화
                        .username("user@abc.com")
                        .password("1234")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder() // 패스워드 암호화
                        .username("admin@abc.com")
                        .password("1234")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Custom Login Page 지정

    @Bean
    public SecurityFilterChain CustomLoginPage(HttpSecurity lendering) throws Exception {

        /** HTTP Security를 통한 HTTP Request 보안 설정
         *
         * 1. csrf 공격 방어 disable
         * 2. 인증방법 지정 - form login
         * 3. 파라미터 -> AuthController의 AuthForm()에 URL 요청 전송
         * 4. 로그인 인증 요청을 수행할 요청 URL 지정, login.html - form 태그 - action의 URL과 동일
         * 5. 인증 실패 시 리다이렉트 할 URI 지정
         * 6. 보안 설정을 메소드 체인 형태로 구성 가능
         * 7. 접근 권한 체크 정의
         * 8-9. 클라이언트의 모든 요청에 대해 접근 허용
         */

        lendering
                .csrf().disable() // 1
                .formLogin() // 2
                .loginPage("/secure/auth-form") // 3
                .loginProcessingUrl("/process_login") // 4
                .failureUrl("/secure/auth-form?error") // 5
                .and() // 6
                .authorizeHttpRequests() // 7
                .anyRequest() // 8
                .permitAll(); // 9

        return lendering.build();
    }
    
    // Request URI 접근 권한 부여

    @Bean
    public SecurityFilterChain AuthorizeRequest(HttpSecurity authorize) throws Exception
    {

        /** exceptionHandling() 부터 설명
         * 
         * 1. 권한없는 사용자가 특정 requestURL에 접근시 표시할 에러 페이지 렌더링 & Exception 처리
         * 2. 람다 표현식을 통한 request URI 접근 권한 부여 - antMatchers 순서 주의 (낮은 권한 순으로 작성 필수)
         * 3. '*'이 1개일 경우 하위 URL의 depth가 1인 URL만 허용
         * 4. 단일 페이지 접근 지정
         * 5. 모든 URL 허용
         */

        authorize
                .csrf().disable()
                .formLogin()
                .loginPage("/secure/auth-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/secure/auth-form?error")
                .and()
                .exceptionHandling().accessDeniedPage("/secure/denied") // 1
                .and()
                .authorizeHttpRequests(authorize -> authorize // 2
                        .antMatchers("/orders/**").hasRole("ADMIN") // 3
                        .antMatchers("/members/my-page").hasRole("USER") // 4
                        .antMatchers("/**").permitAll()); // 5
        return authorize.build();
    }
    
    // LogOut 기능 구현

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity logout) throws Exception {

        /** logout() 부터 설명
         * 
         * 1. 로그아웃 설정을 위한 LogoutConfigurer 리턴
         * 2. 로그아웃을 진행할 Request URL 지정
         * 3. 로그아웃 후 리다이렉트 할 URL 지정
         */

        logout
                .csrf().disable()
                .formLogin()
                .loginPage("/auths/login-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auths/login-form?error")
                .and()
                .logout()                        // 1
                .logoutUrl("/logout")            // 2
                .logoutSuccessUrl("/")  // 3
                .and()
                .exceptionHandling().accessDeniedPage("/auths/access-denied")
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/orders/**").hasRole("ADMIN")
                        .antMatchers("/members/my-page").hasRole("USER")
                        .antMatchers("⁄**").permitAll()
                );
        return logout.build();
    }
}
```