```java
/**
 * 인증과정에서 AuthenticationException이 발생할 경우 이 클래스가 호출되며,
 * 처리하고자 하는 로직을 Override 한 메서드에 구현하면 됨
 */

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");

        // AuthenticationException이 발생하면 ErrorResponder를 생성해서 클라이언트에게 전송
        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);

        logExceptionMessage(authException, exception);
    }

    private void logExceptionMessage(AuthenticationException authException, Exception exception) {
        String message = exception != null ? exception.getMessage() : authException.getMessage();
        log.warn("Unauthorized Error Happened: {}", message);
    }
}
```