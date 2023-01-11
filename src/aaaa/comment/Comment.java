package com.server.seb41_main_11.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.seb41_main_11.post.Post;
import com.server.seb41_main_11.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POST_ID")
    @JsonIgnore
    private Post post;


    @ManyToOne(optional = false)
    @JoinColumn(name = "COUNSELOR_ID")
    @JsonIgnore
    private Counselor counselor;

    void addUser(User user) {
        this.user = user;
    }

    void addPost(Post post) {
        this.post = post;
    }

    void AddCounselor(Counselor counselor) {
        this.counselor = counselor;
    }
}
