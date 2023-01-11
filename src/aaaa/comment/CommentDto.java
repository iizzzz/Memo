package com.server.seb41_main_11.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private long userId;
        private long postId;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private long commentId;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private long commentId;
        private long userId;
        private long counselorId;
        private String content;
    }
}
