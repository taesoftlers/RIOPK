package com.example.hireease.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "access_token_expired_at")
    private Date accessTokenExpiredAt;

    @Column(name = "refresh_token_expired_at")
    private Date refreshTokenExpiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
