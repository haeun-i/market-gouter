package springstudy.spring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springstudy.spring.domain.User;
import springstudy.spring.dto.LoginDto;
import springstudy.spring.repository.UserRepository;
import springstudy.spring.security.JwtTokenProvider;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String,String> user) {
        return userRepository.save(User.builder()
                .userId(user.get("id"))
                .userPassword(passwordEncoder.encode(user.get("password")))
                .userName(user.get("name"))
                .userPhone(user.get("phone"))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getUserNum();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {
        User member = userRepository.findByUserId(dto.getUserId());
        if (!(passwordEncoder.matches(dto.getUserPassword(), member.getUserPassword()))) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUserId(), member.getRoles());
    }
}
