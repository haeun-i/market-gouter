package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springstudy.spring.domain.User;
import springstudy.spring.dto.UserJoinDto;
import springstudy.spring.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomUserDetailService customUserDetailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //userNum으로 회원전체 찾기
    public User findByNum(Long userNum){
        return userRepository.findByUserNum(userNum);
    }

    //userId로 회원전체 찾기
    public User findByUser(String userId){
        return userRepository.findByUserId(userId);
    }

//    //중복 id검사
//    public boolean findById(String id){
//        boolean msg = (userRepository.findByUserId(id) != null );
//        return msg;
//    }



//    회원탈퇴
//
//    public void deleteUser(String id) {
//
//        User user = findByUser(id);
//        this.userRepository.delete(user);
//        //return userRepository.deleteByUserId(userId);
//    }

    //회원수정
    public void modify(String id,UserJoinDto dto){

        User member=findByUser(dto.getUserId());
        // ***** 패스워드값 확인 부분 ****
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("패스워드가 맞지 않습니다.");
        }

    //이름, 폰번호, 패스워드 수정
        member.setUserName(dto.getUserName());
        member.setUserPhone(dto.getUserPhone());
        member.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));

        userRepository.save(member);
    }




    //회원가입
    public String signUp(UserJoinDto userJoinDto) {
        //중복id 검사
        User member = findByUser(userJoinDto.getUserId());
        if (member==null) {
            return "no";
        }
        else {
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

}
