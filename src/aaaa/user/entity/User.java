package com.server.seb41_main_11.user.entity;

import com.server.seb41_main_11.comment.Comment;
import com.server.seb41_main_11.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 6)
    private String birth;

    @Column
    private String image;

    @Column(name = "TOKEN_EXPIRATION_TIME")
    private LocalDateTime tokenExpirationTime;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Payment> pay;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Reserv> reservs;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Notice> notices;

    public void setPayment(Payment payment) {
        this.getPay().add(payment);
        payment.setUser(this);
    }

    public void setReservation(Reserv reserv) {
        this.getReservs().add(reserv);
        reserv.setUser(this);
    }

    public void setPost(Post post) {
        this.getPosts().add(post);
        post.setUser(this);
    }

    public void setComment(Comment comment) {
        this.getComments().add(comment);
        comment.setUser(this);
    }

    public void setNotice(Notice notice) {
        this.getNotices().add(notice);
        notice.setUser(this);
    }

    //---------
    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Notice> notices = new ArrayList<>();

    public void setPost(Post post) {
        this.getPosts().add(post);
        post.setMember(this);
    }

    public void setComment(Comment comment) {
        this.getComments().add(comment);
        comment.setMember(this);
    }

    public void setNotice(Notice notice) {
        this.getNotices().add(notice);
        notice.setMember(this);
    }
}
