package com.server.seb41_main_11.user.entity;

import com.server.seb41_main_11.user.dto.LoginDto;
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

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false, length = 6)
    private String birth;

//    @Column(name = "TOKEN_EXPIRATION_TIME")
//    private LocalDateTime tokenExpirationTime;
//
//    @Column(name = "REFRESH_TOKEN")
//    private String refreshToken;
//
//    @Column
//    private String profileImageUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    private List<Payment> payments;
//
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    private List<Reservation> reservations;
//
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    private List<Posting> postings;
//
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    private List<Comment> comments;
//
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    private List<Notice> notices;
//
//    public void setPayment(Payment payment) {
//        this.getPayments().add(payment);
//        payment.setUser(this);
//    }
//
//    public void setReservation(Reservation reservation) {
//        this.getReservations().add(reservation);
//        reservation.setUser(this);
//    }
//
//    public void setPosting(Posting Posting) {
//        this.getPostings().add(postings);
//        postings.setUser(this);
//    }
//
//    public void setComment(Comment comment) {
//        this.getComments().add(comment);
//        comment.setUser(this);
//    }
//
//    public void setNotice(Notice notice) {
//        this.getNotices().add(notice);
//        notice.setUser(this);
//    }
}
