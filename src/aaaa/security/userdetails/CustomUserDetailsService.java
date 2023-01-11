package com.server.seb41_main_11.security.userdetails;

import com.server.seb41_main_11.security.authorityutils.CustomAuthorityUtils;
import com.server.seb41_main_11.user.entity.User;
import com.server.seb41_main_11.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final com.server.seb41_main_11.user.repository.UserRepository UserRepository;
    private final CustomAuthorityUtils authorityUtils;

    public CustomUserDetailsService(UserRepository UserRepository, CustomAuthorityUtils authorityUtils) {
        this.UserRepository = UserRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optUser = UserRepository.findByEmail(email);

        User findUser = optUser.orElseThrow(EntityNotFoundException::new);

        Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(findUser.getRoles());

        return new CustomUserDetails(findUser);
    }

    private final class CustomUserDetails extends User implements UserDetails {

        CustomUserDetails(User User) {
            setUserId(User.getUserId());
            setName(User.getName());
            setEmail(User.getEmail());
            setPassword(User.getPassword());
            setRoles(User.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getNickname() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}