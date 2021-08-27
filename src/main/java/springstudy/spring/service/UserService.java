package springstudy.spring.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springstudy.spring.domain.User;
import springstudy.spring.dto.UserJoinDto;
import springstudy.spring.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomUserDetailService customUserDetailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //userId로 회원찾기
    public User findByUser(String userId){
        return userRepository.findByUserId(userId);
    }

    //userNum으로 회원찾기
    public User findByNum(Long userNum){
        return userRepository.findByUserNum(userNum);
    }

    //회원가입
    public String signUp(UserJoinDto userJoinDto){

        User user = User.builder()
                .userId(userJoinDto.getUserId())
                .userPassword(passwordEncoder.encode(userJoinDto.getUserPassword()))
                .userName(userJoinDto.getUserName())
                .userPhone(userJoinDto.getUserPhone())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build();
        userRepository.save(user);
        return user.getUserId();
    }

}
