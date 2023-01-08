```java
// Filter의 doFilter()는 상속받은 AbstractAuthenticationProcessingFilter에 존재함
public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // 클라이언트의 로그인 폼을 통해 전송되는 Request Parameter의 Default Username & Password이다
    private static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username;";
    private static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    // 클라이언트의 URL에 매치되는 Matcher
    // AbstractAuthenticationProcessingFilter에 전달되어 Filter가 구체적 작업을 수행할지 다른 Filter를 호출할지 결정
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");

    // Matcher와 AuthenticationManager를 AbstractAuthenticationProcessingFilter에게 전달
    public UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    // 클라이언트에서 전달한 Username & Password를 이용해 인증을 시도하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // HTTP 메서드가 Post가 아니면 throw Exception
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication Method Not Supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // 클라이언트에서 전달한 Username & Password를 이용해 토큰 생성
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);

        // AuthenticationManager의 authenticate() 메서드를 이용해 인증 처리 위임
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
```