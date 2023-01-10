package com.server.seb41_main_11.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.seb41_main_11.user.dto.LoginDto;
import com.server.seb41_main_11.user.entity.User;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Username & Password 기반의 인증을 처리하기위한 상속
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;
//    private final RedisTemplate<String, String> template;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
    }


    // 메서드 내부에서 인증 시도 로직 구현
    @Override
    @SneakyThrows // 메서드 선언부에 throws를 정의 안해도 검사된 예외를 throw할수 있게 해주는 어노테이션
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 역직렬화를 위한 객체
        ObjectMapper objectMapper = new ObjectMapper();

        // Object Mapper를 이용한 역직렬화, JSON -> Java 객체
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        // Username과 Password를 포함한 UsernameAuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // DI 받은 AuthenticationManager 에게 인증 처리 위임
        return authenticationManager.authenticate(authenticationToken);
    }

    // 인증 성공 시 호출될 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        // 엔티티 객체를 얻음
        User user = (User) authResult.getPrincipal();
        // Access Token 생성
        String accessToken = delegateAccessToken(user);
        // Refresh Token 생성
        String refreshToken = delegateRefreshToken(user);

//        // Redis에 저장 - 만료시간 설정을 통한 자동 삭제처리
//        template.opsForValue().set(
//                user.getName(),
//                refreshToken,
//                refreshTokenExpirationMinutes,
//                TimeUnit.MILLISECONDS
//        );

        // Response Header에 Access Token 추가
        response.setHeader("Authorization", "Bearer " + accessToken);
        // Response Header에 Refresh Token 추가
        response.setHeader("Refresh", refreshToken);

    }

    // Private Access Token 생성 로직
    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // Private Refresh Token 생성 로직
    private String delegateRefreshToken(User user) {
        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
