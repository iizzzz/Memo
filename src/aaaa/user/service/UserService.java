package com.server.seb41_main_11.user.service;

import com.server.seb41_main_11.security.authorityutils.CustomAuthorityUtils;
import com.server.seb41_main_11.user.entity.User;
import com.server.seb41_main_11.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    // ----------------- DI ---------------------
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    // ----------------- DI ---------------------
    public User create(User user) {
        verifyExistsEmail(user.getEmail());

        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User update(User user) {
        return null;
    }

    @Transactional(readOnly = true)
    public User find(long userId) {
        return findVerifiedUser(userId);
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size, Sort.by("userId").descending()));
        return users;
    }

    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    public User findVerifiedUser(long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        User findUser = optUser.orElseThrow(EntityNotFoundException::new);

        return findUser;
    }

    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            throw new EntityNotFoundException("유저를 찾을 수 없습니다.");
    }
}
