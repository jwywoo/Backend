package com.willyou.domain.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.willyou.domain.user.dto.SignupRequestDto;
import com.willyou.domain.user.dto.SignupResponseDto;
import com.willyou.domain.user.dto.UserInfoDto;
import com.willyou.domain.user.jwt.JwtUtil;
import com.willyou.domain.user.security.UserDetailsImpl;
import com.willyou.domain.user.service.KakaoService;
import com.willyou.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;

    // 회원 가입
    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new SignupResponseDto("회원가입 성공", 200);
    }

    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        return new UserInfoDto(username);
    }

    // 카카오 로그인
    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String token = kakaoService.kakaoLogin(code);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return "redirect:/";
    }

}