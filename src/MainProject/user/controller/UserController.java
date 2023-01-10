package com.server.seb41_main_11.user.controller;

import com.server.seb41_main_11.security.jwt.JwtAuthenticationFilter;
import com.server.seb41_main_11.user.dto.LoginDto;
import com.server.seb41_main_11.user.dto.UserDto;
import com.server.seb41_main_11.user.entity.User;
import com.server.seb41_main_11.user.mapper.UserMapper;
import com.server.seb41_main_11.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody UserDto.Post post) {
        User user = userService.create(mapper.postToUser(post));

        return new ResponseEntity<>(mapper.entityToResponse(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity patch(@PathVariable("user-id") @Positive long userId,
                                @Valid @RequestBody UserDto.Patch patch) {
        patch.setUserId(userId);

        User update = userService.update(mapper.patchToUser(patch));

        return new ResponseEntity<>(mapper.entityToResponse(update), HttpStatus.OK);
    }
}
