# ⭐ Spring Security 정리

> ## 📌 SecurityConfiguration

### 구조

* @Configuration 클래스 레벨에 적용
* @Bean 메서드 레벨에 적용하여 사용
* UserDetails
* PasswordEncoder -> bcrpt 방식 패스워드 암호화
* InMemoryUserDetailsManager
* SecurityFilterChain

### Bean

* [ UserDetailsManager ] User들을 관리하는 관리자 역할
  * UserDetails
    * 구현체인 User 생성
  * withDefaultPasswordEncoder()
    * 패스워드 암호화
  * roles()
    * 유저레벨 설정
  * return
    * InMemoryUserDetailsManager();

<br>

* [ SecurityFilterChain ] throws Exception
  * HttpSecurity를 파라미터로 가짐
    * .headers().frameOptions().sameOrigin() // H2 를 정상적으로 사용하기 위해 설정
      .and()                   // Chaining
      .csrf().disable()        // csrf 공격 방지 옵션 Disable
      .cors(withDefaults())    //  corsConfiguraationSource Bean을 추가
      .formLogin().disable()   //  SSR 방식의 form-login Disable
      .httpBasic().disable()   //  HTTP Header에 ID,PW를 전송하지 않을것이므로 Disable
      .authorizeHttpRequests(authorize -> authorize .anyRequest().permitAll() // 모든 HTTP Request에 대해 허용
    * return
      * variable.build()

<br>

* [ PasswordEncoder] 패스워드 암호화
  * asswordEncoderFactories.createDelegatingPasswordEncoder() 를통해 PasswordEncoder 객체 생성

<br>

---
> ## 📌 JavaConfiguration

### 구조

* DI를 받은 서비스 클래스들은 @Service를 사용하지않고 JavaConfiguration의 생성자에서 @Bean으로 등록된다
* InMemory User를 등록하기위한 InMemoryMemberService DI
* DB에 User를 등록하기 위한 DBMemberService(@Transactional) DI

> MemberService