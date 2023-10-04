package com.willyou.domain.user.security;

import com.willyou.domain.user.entity.UserEntity;
import com.willyou.domain.user.entity.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final UserEntity userEntity;

    public UserDetailsImpl(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    // 권한 관련 작업을 하기 위한 role return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoleEnum role = userEntity.getRole();
        String authority = role.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    // 계정이 만료되었는지 (true: 만료 x)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었느지 (true : 만료 x)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화여부 (true : 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}