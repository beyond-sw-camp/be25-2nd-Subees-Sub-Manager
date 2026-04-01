package com.subees.backend.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    private UserState userState;

    @Column(name = "email_verified", nullable = false, length = 1)
    private String emailVerified;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    @Builder
    public User(String email, String password, String nickname, UserState userState,
                String emailVerified, LocalDateTime emailVerifiedAt, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.userState = userState;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
        this.createdAt = createdAt;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserState(UserState userState) {
        this.userState = userState;
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}