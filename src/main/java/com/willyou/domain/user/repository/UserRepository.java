package com.willyou.domain.user.repository;

import com.willyou.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByKakaoId(Long kakaoId);
    Optional<UserEntity> findById(Long id);

}
