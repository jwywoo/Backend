package com.willyou.domain.user.entity;

import com.willyou.domain.user.dto.KakaoUserInfoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private Long kakaoId;

    public UserEntity(KakaoUserInfoDto username, String password, String email, UserRoleEnum role, Long kakaoId) {
        this.username = String.valueOf(username);
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
    }

    public UserEntity kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}