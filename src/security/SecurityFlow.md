# ⭐ Security 재정리

> ## 📌 Config
* SecurityConfig
  * 로그인 인증 요청 처리
  * Filter 추가 & 적용
  * CORS 설정
  * Resource별 ACL 설정 - andMatcher 사용 시 순서 조심
  * Authority 등 보안 설정

<br>

> ## 📌 CustomUserDetailsService - DB에서 조회한 User의 인증 정보를 기반으로 인증 처리, Spring Security가 내부적으로 인증 처리 해주는 방식 | 직접 인증 방식은 AuthenticationProvider를 구현하면 됨
* implements 된 UserDetailsService 의 구현메서드인 loadUserByName 메서드로 User의 정보 로드해옴
* 이 클래스 내부에 private CustomUserDetails 생성 - 엔티티를 상속하며, UserDetails를 구현한 클래스, 생성자로 유저의 정보 세팅
* DB에서 User를 조회하고 조회한 User의 권한(Role)을 생성하기 위해 UserRepository, CustomAuthorityUtils를 DI받음
* AuthorityUtils을 이용해 Role 기반의 GrantedAuthority 컬렉션 생성
* 위에서 생성한 인증정보, 권한정보를 Spring Security는 아직 모르므로 UserDetails의 구현체인 User 클래스의 객체를 통해 제공
* 즉, DB의 User 인증정보만 Spring Security 에게 넘겨주고 인증처리는 Spring Security 가 처리해줌
* (UserDetails는 UserDetailsService에 의해 load 되어 인증을 위해 사용되는 핵심 User 정보를 표현하는 인터페이스)
* (UserDetails 인터페이스의 구현체는 Spring Security에서 보안 정보 제공을 목적으로 직접 사용되지는 않고, Authentication 객체로 캡슐화 되어 제공됨)

> ## 📌 CustomAuthorityUtils - User의 권한을 생성, 매핑하는 클래스
* 필드에 관리자의 정보를 yml의 프로퍼티로 가져옴
* AuthorityUtils 클래스를 이용해 권한목록을 List<GrantedAuthority> 객체로 생성

> ## 📌 JWT
* JwtTokenizer - 토큰 생성

> ## 📌 Filter
* JwtAuthenticationFilter - 로그인 인증정보를 직접 수신하여 처리하는 필터
  * SecuriyConfig에 추가해야함
  * 인증에 성공할 경우 토큰 생성
  * 인증 시도 로직 -> 인증 성공 시 Authentication 객체 만들어지고 principal 필드에 Member 객체 할당
  * Access & Refresh 토큰 생성

<br>

* JwtVerificationFilter - JWT 검증 필터
  * SecuriyConfig에 추가해야함
  * Authentication 객체에 채울 유저의 권한 생성
  * JWT의 Claims를 파싱해서 Signature 검증
  * Authentication 객체를 SecurityContext에 저장
  * SignatureException, ExpiredException에 대한 예외 처리

<br>

> ## 📌 Handler
* AuthenticationSuccessHandler - 인증 성공 시 사용자 정보를 response 로 전송 등 추가 처리, QnA 프로젝트에선 로깅 용도로 사용
  * AuthenticationFilter, SecurityConfig 에 추가해야함

<br>

* AuthenticationFailureHandler - 에러 정보를 Json으로 변환해 에러 정보를 클라이언트에게 전송
  * AuthenticationFilter, SecurityConfig 에 추가해야함

<br>

* AuthenticationEntryPoint - AuthenticationException이 발생될때 호출되는 핸들러
  * SecurityConfig의 Security Filter Chain에 추가해야함
  * 인증 과정에서 AuthenticationException이 발생할 경오 ErrorResponse를 생성해서 클라이언트에게 전송

* AccessDeniedHandler - 인증은 성공했지만 리소스에 대한 권한이 없을 경우 호출되는 핸들러
  * SecurityConfig의 Security Filter Chain에 추가해야함
  * AccessDeniedException이 발생하면 ErrorResponse를 생성해서 클라이언트에게 전송

---

# 대충 써놓은것들

* UserDetailsManager - Spring Security의 User를 관리, 하위 타입 -> InMemoryUserDetailsManager

* UserDetails 로 User 생성, User 정보 관리

* USerDetailService - User 정보를 로드하는 인터페이스

* UserDetailsService - 유저 정보 로드

* PasswordEncoder - 패스워드 암호화

* AuthorityUtils - 권한 목록 생성용 클래스

  * 리턴 List<GrantedAuthority>

* HelloAuthorityUtils

  * 유저의 권한을 생성, 매핑

* HelloUserDetailsService implements UserDetailsService

  * DB에서 User를 조회하고, 조회한 USer의 권한 정보를 생성하기 위해 MemberRepository와

    * HelloAuthorityUtils 클래스를 주입

* DBMemberService implements MemberService

  * User의 인증 정보를 DB에 저장

* Spring Security에서 SimpleGrantedAuthority 를 사용해 Role 베이스 형태의 권한을 지정할 때

* ‘Roll_’ + 권한명 형태로 지정해야함

* Custom UserDetailService - Spring Security 내부 인증 처리

* AuthenticationProvider - 직접 인증 처리

* AuthenticationSuccessHandler, AuthenticationFailureHandler - JWT 인증 성공, 실패 시 추가 작업