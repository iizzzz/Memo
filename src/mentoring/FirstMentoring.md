
# ⭐ 2023.01.08 11:00 ~ 12:00 멘토링

> ## 📌 Security

* 특정 API를 조회할 때 멤버의 Role을 가져와서 검증
  * 이 방식의 문제점 = 같은 롤이 들어왔을때의 구분 어려움
  * 임시 해결책 = Role 값 자체의 해시화

* 로그인(토큰 발급)
* 토큰을 검증하는 JwtTokenizerAuthenticationFilter 구현

* URL별 권한 & CORS & 암호화알고리즘 지정 등 보안 설정
  * SecurityConfiguration 클래스 내부에 보안설정

* UserRole 분할
  * CustomAuthorityUtils 클래스에 정의

* 토큰 생성
  * JwtTokenizer

* OAuth2
  * OAuth2ClientSuccessHandler & OAuth2ClientFailerHandler 구현
  * ErrorResponder & AuthenticationEntryPoint 등은 Optional하게 추가할지 말지 판단


> ## 📌 순환참조 발생 가능성

* 엔티티간 양방향 참조 관계일때 - 엔티티 순환참조 문제 발생
* 서비스 간 DI를 받는 상황일때 - DI 순환참조 문제 발생


> ## 📌 테스트 & RestDocs

* TDD 주도 개발 = 기능의 정상동작이 실패할만한 케이스들에 대한 테스트 + 성공테스트

* Swagger
  * Annotation 기반 API문서화, 빠른 API 문서화 가능
  * 장점 = 하나의 API 개발이 완료된 시점에 바로 해당 API에대한 문서가 나옴

* RestDocs
  * 느린 API문서 제공
  * 모든 기능 테스트가 완료되어야 스니핏들이 생성이 되기때문에 안정성 ↑
  * 프론트의 빠른 API요청에 대한 임시방편 = 기본적인 CRUD + Stub Data를 이용해 API문서 생성