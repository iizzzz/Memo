package com.server.seb41_main_11.program;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProgramDto {

    public static class Post {
        private long counselorId;
        private String title;
        private String content;
        private int cost;
        private String image;
        private int userCount;
        private int userMax;
        private LocalDateTime dateStart;
        private LocalDateTime dateEnd;
        private String announce;
        private String zoomLink;
        private String symptomTypes;
    }

    public static class Patch {
        private long programId;
        private String title;
        private String content;
        private int cost;
        private String image;
        private int userCount;
        private int userMax;
        private LocalDateTime dateStart;
        private LocalDateTime dateEnd;
        private String announce;
        private String zoomLink;
        private String symptomTypes;
    }

    public static class Response {
        private String title;
        private String content;
        private int cost;
        private String image;
        private int userCount;
        private int userMax;
        private LocalDateTime dateStart;
        private LocalDateTime dateEnd;
        private String announce;
        private String zoomLink;
        private List<Program.SymptomTypes> symptomTypes;
    }
}
