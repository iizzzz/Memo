# Security Flow

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