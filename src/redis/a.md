```java
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  ...
  private final RedisTemplate<String, String> redisTemplate;

  @Value("${jwt.token.refresh-token-expire-length}")
  private long refresh_token_expire_time;

 ...

  @Override
  public ResponseEntity<TokenDto> signIn(SignInReq signInReq) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              signInReq.getEmail(),
              signInReq.getPassword()
          )
      );

      String refresh_token = jwtTokenProvider.generateRefreshToken(authentication);

      TokenDto tokenDto = new TokenDto(
          jwtTokenProvider.generateAccessToken(authentication),
          refresh_token
      );

      // Redis에 저장 - 만료 시간 설정을 통해 자동 삭제 처리
      redisTemplate.opsForValue().set(
              authentication.getName(),
              refresh_token,
              refresh_token_expire_time,
              TimeUnit.MILLISECONDS
          );

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("Authorization", "Bearer " + tokenDto.getAccess_token());

      return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid credentials supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  @Override
  public ResponseEntity<TokenDto> regenerateToken(RegenerateTokenDto refreshTokenDto) {
    String refresh_token = refreshTokenDto.getRefresh_token();
    try {
      // Refresh Token 검증
      if (!jwtTokenProvider.validateRefreshToken(refresh_token)) {
        throw new CustomException("Invalid refresh token supplied", HttpStatus.BAD_REQUEST);
      }

      // Access Token 에서 User email를 가져온다.
      Authentication authentication = jwtTokenProvider.getAuthenticationByRefreshToken(refresh_token);

      // Redis에서 저장된 Refresh Token 값을 가져온다.
      String refreshToken = redisTemplate.opsForValue().get(authentication.getName());
      if(!refreshToken.equals(refresh_token)) {
        throw new CustomException("Refresh Token doesn't match.", HttpStatus.BAD_REQUEST);
      }

      // 토큰 재발행
      String new_refresh_token = jwtTokenProvider.generateRefreshToken(authentication);
      TokenDto tokenDto = new TokenDto(
          jwtTokenProvider.generateAccessToken(authentication),
          new_refresh_token
      );

      // RefreshToken Redis에 업데이트
      redisTemplate.opsForValue().set(
          authentication.getName(),
          new_refresh_token,
          refresh_token_expire_time,
          TimeUnit.MILLISECONDS
      );

      HttpHeaders httpHeaders = new HttpHeaders();

      return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid refresh token supplied", HttpStatus.BAD_REQUEST);
    }
  }
}
```