```java
/**
 * 인증 성공 시 추가작업을 할 수 있는 클래스
 */

@Slf4j
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("# Authentication Successfully!");
    }
}

```