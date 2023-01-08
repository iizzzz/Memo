# ⭐ Spring Security 정리

> ## 📌 인증 구조

1. 로그인 요청 -> UsernamePasswordAuthenticationFilter가 받음

2. UsernamePasswordAuthenticationFilter가 UsernamePasswordAuthenticationToken 생성
   (이 토큰은 Authentication을 구현한 클래스이며 인증이 되지않은 Authentication임)

3. 필터가 Authentication을 AuthenticationManager(인증처리 총괄)에게 전달
   (ProviderManager = AuthenticationManager의 구현클래스, 인증 위임 총괄)

4. ProviderManager가 AuthenticationProvider에게 인증이 되지않은 Authentication 전달

5. AuthenticationProvider가 UserDetailsService를 이용해 UserDetails 조회
   (UserDetails는 DB등의 저장소에 저장된 유저의 정보를 담고있는 컴포넌트)

6. DB에서 조회한 유저의 정보,크리덴셜 등으로 UserDetails를 생성 후 AuthenticationProvider에게 다시 전달

7. UserDetails를 받은 AuthenticationProvider는 PasswordEncoder를 이용해 (UserDetails의 PW == Authentication의 PW)를 검증, 인증에 실패 시 Exception throw

8. AuthenticationProvider는 인증된 Authentication을 ProviderManager에게 전달
   (principal, credential, grantedAuthorities를 가지고 있는 Authentication)

9. 인증된 Authentication을 UsernamePasswordAuthenticationFilter에게 전달

10. SecurityContextHolder를 이용해 SecurityContext에 인증된 Authentication 저장

<br>

> ## 📌 인가 구조

1. URL을 통해 사용자의 액세스를 제한하는 AuthorizationFilter는 SecurityContextHolder로부터 Authenticaton을 획득
   (권한 부여 필터)

2. 얻은 Authentication과 HttpServletRequest를 AuthorizationManager 에게 전달

3. Authorization의 구현체인 RequestMatcherDelegatingAuthorizationManager는 RequestMatcher 평가식을 기반으로 해당 평가식에 매치되는 AuthorizationManger에게 권한부여 처리를 위임

4. RequestMatcherDelegatingAuthorizationManager의 직접 처리가 아닌 AuthorizationManager의 다른 구현체에게 권한처리 위임

5. 내부에서 매치되는 AuthorizationManager의 구현 클래스가 있다면 해당 구현 클래스가 사용자의 권한을 체크

6. 적절한 권한이 아닐 시 AccessDeniedException throw 되고 ExceptionTranslationFilter가 Exception 처리

7. 적절한 권한일 시 다음 요청 프로세스 계속 이어나감


<br>

> ## 📌 JWT 구조 (2,3,5,7 구현), (4,6은 Spring Security의 AuthenticationManager가 처리)

1. 클라이언트가 서버에 로그인 인증 요청(Username & Password를 서버에 전송)

2. 로그인 인증을 담당하는 Security Filter(JwtAuthenticationFilter)가 로그인 인증 정보 수신

3. Security Filter가 수신한 로그인 정보를 AuthenticationManager에게 전달해 인증 처리를 위임

4. AuthenticationManager가 CustomUserDetailsService에게 사용자의 UserDetails 조회 위임

5. CustomUserDetailsService가 사용자의 크리덴셜을 DB에서 조회 후, AuthenticationManager에게 UserDetails 전달

6. AuthenticationManager가 로그인 인증 정보와 UserDetails의 정보를 비교해 인증 처리

7. JWT 생성 후, 클라이언트에게 전달

<br>

> ## 📌 OAuth2 + JWT

> ### 구조

* ClientRegistration은 OAuth 2 시스템을 사용하는 Client 등록 정보를 표현하는 객체이다.

* Spring Security에서 제공하는 CommonOAuth2Provider enum은 내부적으로 Builder 패턴을 이용해 ClientRegistration 인스턴스를 제공하는 역할을 한다.

* OAuth2AuthorizedClientService는 권한을 부여받은 Client인 OAuth2AuthorizedClient를 관리하는 역할을 한다.

* OAuth2AuthorizedClientService를 이용해서 OAuth2AuthorizedClient 가 보유하고 있는 Access Token에 접근할 수 있다.

* OAuth2AuthorizedClientService의 loadAuthorizedClient("google", authentication.getName())를 호출하면 OAuth2AuthorizedClientRepository를 통해 OAuth2AuthorizedClient 객체를 로드 할 수 있다.

> ### 인증 처리 흐름 JWT + OAuth2, 6~11은 Spring이 내부적으로 처리해줌

(1) Resource Owner가 웹 브라우저에서 ‘Google 로그인 링크’를 클릭합니다.

(2) Frontend 어플리케이션에서 Backend 어플리케이션의 http://localhost:8080/oauth2/authorization/google로 request를 전송합니다. 이 URI의 requet는 OAuth2LoginAuthenticationFilter 가 처리합니다.

(3) Google의 로그인 화면을 요청하는 URI로 리다이렉트 
이 때 Authorization Server가 Backend 어플리케이션 쪽으로 Authorization Code를 전송할 
Redirect URI(http://localhost:8080/login/oauth2/code/google)를 쿼리 파라미터로 전달
Redirect URI는 Spring Security가 내부적으로 제공함

(4) Google 로그인 화면을 오픈

(5) Resource Owner가 Goole 로그인 인증 정보를 입력해서 로그인을 수행

(6) 로그인에 성공하면 (3)에서 전달한 Backend Redirect URI(http://localhost:8080/login/oauth2/code/google)로 Authorization Code를 요청

(7) Authorization Server가 Backend 어플리케이션에게 Authorization Code를 응답으로 전송

(8) Backend 어플리케이션이 Authorization Server에게 Access Token을 요청

(9) Authorization Server가 Backend 어플리케이션에게 Access Token을 응답으로 전송
여기서의 Access Token은 Google Resource Server에게 Resource를 요청하는 용도로 사용

(10) Backend 어플리케이션이 Resource Server에게 User Info를 요청합니다.
여기서의 User Info는 Resource Owner에 대한 이메일 주소, 프로필 정보 등을 의미

(11) Resource Server가 Backend 어플리케이션에게 User Info를 응답으로 전송

(12) Backend 어플리케이션은 JWT로 구성된 Access Token과 Refresh Token을 생성한 후, 
Frondend 어플리케이션에게 JWT(Access Token과 Refresh Token)를 전달하기 위해 
Frondend 어플리케이션(http://localhost?access_token={jwt-access-token}&refresh_token={jwt-refresh-token})으로 Redirect

<br>

> ## 📌 구현

### 필요한 클래스

* SecurityConfig
* CustomDetailsService
* CustomJwtAuthenticationFilter
* CustomUserDetailsService
* CustomAuthorityUtils
* JwtTokenizer
* Success & Failer Handler 구현

1. 엔티티에 password, List<String>roles 필드 추가(@ElementCollection(fetch = FetchTYpe.EAGER))

2. DTO에 password 필드 추가

3. CustonAuthorityUtils, SecurityConfig(HttpSecurity, CORS, Filter 등 보안설정) 작성

4. Service에서 PasswordEncoder, CustomAuthorityUtils DI받기

5. Service의 create()에 패스워드 암호화 로직 작성후 암호회된 비밀번호 엔티티에 삽입

6. Service의 create()에 List로 권한 생성 후 엔티티에 삽입 

7. 
