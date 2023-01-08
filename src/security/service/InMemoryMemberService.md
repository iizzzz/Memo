```java

package com.qna.security.config;

import com.qna.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* InMemory User 등록을 위한 클래스 */

public class InMemoryMemberService implements CustomMemberService {

    private final UserDetailsManager userDetailsManager; // 유저관리 매니저
    private final PasswordEncoder passwordEncoder; // 패스워드 암호화

    public InMemoryMemberService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member createMember(Member member) {

        // 유저의 권한 생성
        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());
        // 패스워드 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        // 유저를 등록하기 위한 UserDetails 생성
        UserDetails userDetails = new User(member.getEmail(), encryptedPassword, authorities);
        // DI 받은 UserDetailsManager의 createUser()를 이용한 User 등록
        userDetailsManager.createUser(userDetails);

        return member;
    }

    // 유저의 권한 생성 & 지정
    private List<GrantedAuthority> createAuthorities(String... roles) {
        // 생성자 파라미터로 해당 User의 Role을 전달하면서 SimpleGrantedAuthority 객체 생성
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList());
    }
}

```