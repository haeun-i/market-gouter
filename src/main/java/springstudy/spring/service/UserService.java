package springstudy.spring.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.User;
import springstudy.spring.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기전용
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional //쓰기가 주
    public Long join(User user){

        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserId();
    }

    //id 중복검사
    private void validateDuplicateUser(User user){
        List<User> findUserIds = userRepository.findById(user.getUserId());
        if (!findUserIds.isEmpty()){
            throw new IllegalArgumentException("이미 존재하는 id입니다");
        }
    }

    //id 회원 단건 조회
    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }


}
