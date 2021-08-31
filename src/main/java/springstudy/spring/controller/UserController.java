package springstudy.spring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springstudy.spring.domain.User;
import springstudy.spring.dto.LoginDto;
import springstudy.spring.dto.UserJoinDto;
import springstudy.spring.repository.UserRepository;
import springstudy.spring.response.DefaultRes;
import springstudy.spring.response.ResponseMessage;
import springstudy.spring.response.StatusCode;
import springstudy.spring.security.JwtTokenProvider;
import springstudy.spring.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;

    // 회원가입
    //@Valid를 작성하면, @RequestBody로 들어오는 객체(dto)에 대한 검증을 수행
    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody UserJoinDto userJoinDto) {

        userService.signUp(userJoinDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.UNAUTHORIZED,
                ResponseMessage.CREATED_USER, userJoinDto), HttpStatus.OK);

    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {
        User member = userService.findByUser(dto.getUserId());

        //해당 user의 id가 존재하지 않는 경우
        if(member == null)

        if (!(passwordEncoder.matches(dto.getUserPassword(), member.getUserPassword()))) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUserId(), member.getRoles());
    }
}
