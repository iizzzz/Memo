```java
package com.codestates.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 애플리케이션 실행시 해당 애너테이션이 붙은 클래스의 내용들을 토대로 구성해준다.
public class SecurityConfigration {
// Spring Securtity의 설정을 진행한다.
//    @Bean
//    public UserDetailsManager userDetailsService() {
//        // 인증된 사용자의 핵심정보를 포함하는 인터페이스 UserDetails
//        UserDetails userDetails =
//                User.withDefaultPasswordEncoder()           // UsserDatails를 구현한 User(소스코드 확인시 ㄹㅇ임)
//                        // withDefaultPasswordEncoder() 지금 deprecated..
//                        // 디폴트 패스워드 인코더를 이용해서 사용자 패스워드를 암호화해준다. 두줄 밑의 password()에 들어간 매개변수 "1111"을
//                        // 암호화 해준다.
//                        .username("SsangSoo@github.com")    // username(사용자 고육의 식별자,  즉 사용자 아이디 같은 것.
//                        .password("1111")                   // 사용자의 password를 설정. withDefaultPasswordEncoder()로 인해 암호화됨.
//                        .roles("USER")                      // 사용자의 역할을 지정하는 메서드.
//                        .build();                           //
//
//
//        return new InMemoryUserDetailsManager(userDetails);
//        // UserDetails 인터페이스 타입의 객체들을 매개변수로 받아서,
//        // InMemoryUserDetailsManager객체를 생성한다.
//        // 그리고, 반환타입으로 되어있는 UserDetailsManager는 인터페이스이고, UserDetails를 관리한다.
//        // InMemoryUserDetailsManager는 UserDetailsManager인터페이스를 구현한 구현체이다.
//    }
// 위의 내용 리팩터링 되어있음.
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .password("1111")  // .withDefaultPasswordEncoder()에 의해서 인코딩됨.
//                        .username("SsangSoo@github.com")
//                        .roles("USER")
//                        .build();
//
//
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .password("2222")   // .withDefaultPasswordEncoder()에 의해서 인코딩됨.
//                        .username("admin@gmail.com")    // username(Id)
//                        .roles("ADMIN")
//                        .build();
//
//
//        // UserDetails 인터페이스 타입을 InMemoryUserDetailsManager의 매개변수로 넣으면서
//        // 유저 생성함.
//        return new InMemoryUserDetailsManager(user, admin); // 매개변수를 가변으로 받고있음..
//    }

    // 패스워드 인코더를 Bean으로 등록.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // .createDelegatingDelegatingPasswordEncoder를 통해서 DelegatingPasswordEncoder를 생성한다.
        // DelegatingPasswordEncoder는 인터페이스인 PasswordEncoder의 구현체이다.
        // 기본적으로 Spring Security에서 지원하는 Password의 Default 암호화 알고리즘은 bcrypt이다.
            // DelegatingPasswordEncoder를 사용해 PasswordEncoder를 생성하는 방법
    }
//
//
//
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        // HttpSecurity를 통해 HTTP 요청에 대한 보안 설정을 구성한다.
////            // HttpSecurity는 HTTP 요청에 대한 보안 설정을 구성하기 위한 핵심 클래스다.
////        // 스프링 시큐리티 5.7 이전 버전에서는 HTTP 보안설정 구성을 위해 WebSecurityConfigurerAdapter를 상속해서
////        // HTTP 보안 설정을 구성했지만, 5.7 이후버전부터는 Deprecated되었다.
////        // 5.7 이후버전부터는 SecurityFilterChain을 Bean으로 등록해서 HTTP 보안설정을 구성한다.
////
////        http
////                .csrf().disable()                       // CSRF(사용자가 어떤 특정한 행동을 할 것을 예측하고,
////                                                        // 스크립트를 넣어서 해커가 원하는대로 서버에서 동작하게 하는 것)
////                                                        // 여튼 그 CSRF의 공격에 대한 시큐리티 설정을 비활성화한다.
////                                                            // 기본적으로 Spring Securtity는 기본적으로 아무설정을 하지 않으면,
////                                                            // csrf 공격을 방지하기 위해 클라이언트로부터 CSRF Token을 수신 수 검증한다.(똑똑함)
////                                                                // 지금은 학습 진행 중이므로, CSRF 토큰 검증 할 필요 없으므로, 일단 비활성화
////                                                                // 이 설정을 하지 않으면, 403 에러로 해 접속이 불가하다.
////                .formLogin()                            // 인증 방식을 formlogin으로 설정
////                .loginPage("/auths/login-form")         // 로그인 페이지의 URL을 설정한다.(커스텀 로그인 페이지 지정)
////                                                            // 해당 페이지는 AuthController의 loginForm() 메서드와 매핑된 URL이다.
////                .loginProcessingUrl("/process_login")   // 로그인 인증 요청을 수행할 요청 URL을 지정한다.
////                                        /*
////                                            <body>
////                                                <div class="container" layout:fragment="content">
////                                                    <form action="/process_login" method="post">
////                                                        <div class="row">
////                                             // login.html의 11번 라인에 지정되어있다.
////                                        */
////                                        // loginPage에서 view이름 "login"을 리턴하면, login.html로 view를 보여주고,
////                                        // login.html에서 "/process_login"을 지정해준다
////                                            // "/process_login"은 Spring Security에서 내부적으로 인증 프로세스를 진행한다.
////                .failureUrl("/auths/login-form?error")  // 로그인을 실패할 경우에 대한 view URL 지정.
////                                                                            // 학습자료와 같이 html에 코드 추가.
////                .and()                                  // 이 메서드를 통해서 Spring Security의 보안 설정을 메서드 체인 형식으로 구성가능하다.
////                                                            // 따라서 밑의 코드부터는 메서드 체인형식으로 구성된다.
////                .authorizeHttpRequests()                // * authorize(인가) // 이 메서드로 인해서 클라이언트의 요청이 오면, 접근권한에 대한 확인을 한다.
////                .anyRequest()                           // 어떤 요청이든
////                .permitAll();                           // 접근을 허용한다. 일단은 모든 접근을 허가함. (나중에 제한설정함.)
////
////        // 로그인 페이지를 만들어둔 로그인 페이지로 사용하기 위한 (최소한의) 설정만 추가한 코드
////        return http.build();
////    }
//
//    // 위에 작성한 SecurityFilterChain의 접근권한을 제한하는 코드를 작성
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()   // csrf에 대한 시큐리티 설정 비활성화(지금은 그럴 필요가 없기 때문)
////                .formLogin()        // 폼로그인 방식으로 설정.
////                .loginPage("/auths/login-form") // 로그인 페이지 설정
////                .loginProcessingUrl("/process_login")   // 로그인 인증 요청을 수행할 url (Spring Security에서 내부적으로 인증프로세스 진행)
////                .failureUrl("/auths/login-form?error")  // 로그인 실패시 url
////                .and()  // 메서드 체인 방식으로 보안설정 구성 // 아래 코드들부터 적용 // 로그인 적용 됬으면, 밑으로 실행.
////                .exceptionHandling().accessDeniedPage("/auths/access-denied")  // 권한이 없어 접근이 거부된 사용자에게 보여줄 페이지 URL지정
////                                                                                                // Exception을 처리하는 기능을 함.
////                                                                                                // exceptionHandling() 메서드는 ExceptionHandlingConfigurer를 리턴하는데,
////                                                                                                // 이를 통해서 Exception을 처리할 수 있다.
////                                                                                              // accessDeniedPage() 메서드는 403 에러 발생시, 파라미터로 지정한 URL로 리다이렉트 되도록 해준다.
////                                                                                                // 파라미터를 통해서 보여줄 view 'access-denied.html' 파일이다.
////                .and()
////                .authorizeHttpRequests(authorize -> authorize                       // 해당 메서드는 람다 표현식으로 request URI에 대한 접근 권한을 부여할 수 있다.
////                        .antMatchers("/orders/**").hasRole("ADMIN")      // `antMatchers()`는 ant라는 빌드 툴에서 사용되는 `Path Pattern`을 이용해서 URL을 표현한다.
////                                                                                        // 'ADMIN' 권한을 받은 사용자만 "/orders"의 모든 하위 URL에 접근할 수 있다는 의미.
////                        .antMatchers("/members/my-page").hasRole("USER") // antMatchers의 페이지에 해당하는 URL에 "USER"만 접근가능함.
////                        .antMatchers("/**").permitAll()                  // 앞에서 지정한 페이지 URL 외에 모든 URL은 모든 요청 허용.
////                                                                                        // 순서가 중요하다! permitAll이 먼저 나오면, 위의 두줄을 그 밑에 썻더라도 적용이 안된다!
////                );
////        return http.build();
////    }
//    // 위의 모든 내용에 logout까지
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()   // csrf 비활성화(지금 필요x)
//                .formLogin()        // form 로그인 방식
//                .loginPage("/auths/login-form") // 로그인 페이지
//                .loginProcessingUrl("/process_login")   // 스프링시큐리티 내부처리방식으로 로그인 인증을 진행한다.
//                .failureUrl("/auths/login-form?error") // 로그인실패시 나오는 URL(view)
//                .and()                                  // 이 메서드를 통해서 Spring Security의 보안 설정을 메서드 체인 형식으로 구성가능하다.
//                .logout()                               // 로그아웃 할 때 호출하는 메서드 / 해당 메서드의 반환타입은 LogoutConfigurer
//                .logoutUrl("/logout")                   // 로그아웃을 하기 위한 URL을 지정한다.
//                .logoutSuccessUrl("/")                  // 로그아웃을 성공한 후에 리다이렉트할 URL
//                .and()
//                .exceptionHandling().accessDeniedPage("/auths/access-denied")
//                .and()
//                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers("/orders/**").hasRole("ADMIN")
//                        .antMatchers("/members/my-page").hasRole("USER")
//                        .antMatchers("/**").permitAll()
//                );
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                // frameOptions() HTML 태그 중 <frame>이나 <iframe>,<Object> 태그에서 페이지를 렌더링 할지의 여부를 결정함.
                    // Spring Security에서 `Clickjacking` 공격을 막기 위해 기본적으로 frameOptions() 기능이 활성화 되어있고, Default값은 `DENY`이다.
                    // 즉, HTML 태그를 이용한 페이지 렌더링을 허용하지 않음(기본적으로 DENY)
                    // `.frameOptions().sameOrigin()`을 호출하면, 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용한다는 의미.
                // * H2 웹 콘솔을 사용하려면, 이와 같은 설정을 해주면된다.

                .and()                          // -
                .csrf().disable()                       // csrf 보안 비활성화(지금 필요없음)
                .formLogin()                            // 폼로그인 방식.
                .loginPage("/auths/login-form")         // 로그인 페이지 url(view)
                .loginProcessingUrl("/process_login")   // 로그인 인증을 수행할 페이지 url(view)
                .failureUrl("/auths/login-form?error") // 로그인 실패시 url(view)
                .and()                          // -
                .logout()                               // 로그아웃
                .logoutUrl("/logout")                   // 로그아웃 페이지
                .logoutSuccessUrl("/")                  // 로그아웃 성공시 페이지
                .and()                          // -
                .exceptionHandling().accessDeniedPage("/auths/access-denied")   // 예외발생히 접근실패시 페이지.
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/orders/**").hasRole("ADMIN")         // /orders의 하위 페이지는 관리자만 허용
                        .antMatchers("/members/my-page").hasRole("USER")    // my-page는 유저만,
                        .antMatchers("/**").permitAll()                     // 나머지 요청은 다 허용.
                );
        return http.build();
    }

}
```