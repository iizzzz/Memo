package com.server.seb41_main_11.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.seb41_main_11.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int views;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Kind kinds;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNSELOR_ID")
    @JsonIgnore
    private Counselor counselor;

    void addCounselor(Counselor counselor) {
        this.counselor = counselor;
        if (!this.counselor.getPosts().contains(this)) {
            this.counselor.getPosts().add(this);
        }
    }

    void addUser(User user) {
        this.user = user;
        if (!this.user.getPosts().contains(this)) {
            this.user.getPosts().add(this);
        }
    }

    public enum Kind {
        NOTICE("공지사항"),
        GENERAL("일반글"),
        COUNSELOR("상담글");

        @Getter
        private String status;

        Kind(String status) {
            this.status = status;
        }
    }
}
