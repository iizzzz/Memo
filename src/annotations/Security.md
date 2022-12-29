# 📌 [ OAuth2 Annotations ]
***
<br>

> ⭐ @ElementCollection
- 속성 fetch = FetchType.EAGER
- 유저 등록시, 유저의 권한 테이블 생성

<br>

> ⭐ @AuthenticationPrincipal
- OAuth2User 타입으로 OAuth2User 객체를 얻음

<br>

> ⭐ @RegisteredOAuth2AuthorizedClient("google")
- OAuth2AuthorizedClient 타입으로 파라미터로 OAuth2 AccessToken 객체를 얻음

<br>

> ⭐ @CrossOrigin
- CORS 설정