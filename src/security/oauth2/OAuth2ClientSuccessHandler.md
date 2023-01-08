```java
package com.qna.security.oauth2;

import com.qna.entity.Member;
import com.qna.entity.Stamp;
import com.qna.security.userdetails.CustomAuthorityUtils;
import com.qna.service.MemberService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 이 클래스는 OAuth2 인증 후, Front쪽으로 JWT를 전송하는 핵심 클래스
 *  SimpleUrlAuthenticationSuccessHandler의 getRedirectStrategy().sendRedirect()를 이용해 손쉬운 리다이렉트
 */

public class OAuth2ClientSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    public OAuth2ClientSuccessHandler(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 타입 추론
        var oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Authentication 객체에서 얻은 OAuth2 User 객체에서 Resoutce Owner의 이메일 주소를 얻음
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));

        // DI 받은 CustomAuthorityUtils를 이용해 권한 정보 생성
        List<String> authorities = authorityUtils.createRoles(email);

        // Resource Owner의 이메일을 DB에 저장
        saveMember(email);

        // Access Token, Refresh Token을 생성해 Front로 전달하기 위해 Redirect 처리
        redirect(request, response, email, authorities);
    }

    private void saveMember(String email) {
        Member member = new Member(email);
        member.setStamp(new Stamp());
        memberService.createMember(member);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities) throws IOException {

        // Access Token 생성
        String accessToken = delegateAccessToken(username, authorities);

        // Refresh Token 생성
        String refreshToken = delegateRefreshToken(username);

        // UriComponentBuilder 를 이용해 Front 쪽의 URL 생성
        String uri = createURI(accessToken, refreshToken).toString();

        // Front로 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    // Access Token 생성
    private String delegateAccessToken(String username, List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);

        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // Refresh Token 생성
    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    // URL 생성
    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(80) // Front 서버인 Apache의 포트
                .path("/receive-token.html")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}

```