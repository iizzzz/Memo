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
        private String password;
//        private String confirmPassword;
        private String name;
        private String username;
        private String birth;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private long userId;
        private String username;
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
        private String name;
        private String username;
        private String email;
        private String password;
        private String birth;
        private LocalDateTime tokenExpirationTime;
        private String refreshToken;
        private String profile;
        private List<String> roles;
//        private List<Payment> payments;
//        private List<Reservation> reservations;
//        private List<Posting> postings;
//        private List<Comment> comments;
//        private List<Notice> notices;
    }
}
