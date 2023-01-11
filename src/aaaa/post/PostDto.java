package com.server.seb41_main_11.domain.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class userPost {
        private long memberId;
        private String title;
        private String content;
        private String kinds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class counselorPost {
        private long counselorId;
        private String title;
        private String  content;
        private String kinds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private long postId;
        private String title;
        private String content;
        private String kinds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private long postId;
        private long memberId;
        private long counselorId;
        private String title;
        private String content;
        private String kinds;
        private int views;
    }
}
