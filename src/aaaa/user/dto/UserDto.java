package com.server.seb41_main_11.user.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private String email;
        @Pattern(regexp = "^(?=.[A-Za-z])(?=.\d)[A-Za-z\d]{8,}$",
                message = "비밀번호는 최소 8자이상 문자와 숫자가 들어가야 합니다")
        private String password;
        private String name;
        private String nickname;
        @Pattern(regexp = "^([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))$",
                message = "생년월일은 6자리 숫자를 순서대로 입력해야 합니다.")
        private String birth;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private long userId;
        private String nickname;
        private String prePassword;
        private String newPassword;
        private String modifiedPassword;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long userId;
        private String email;
        private String password;
        private String name;
        private String nickname;
        private String birth;
        private String image;
        private LocalDateTime tokenExpirationTime;
        private String refreshToken;
        private List<String> roles;
    }
}
