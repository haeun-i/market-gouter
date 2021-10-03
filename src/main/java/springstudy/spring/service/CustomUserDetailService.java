package springstudy.spring.service;




import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springstudy.spring.domain.User;
import springstudy.spring.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override //실제 DB의 회원정보를 가져오는 로직 -> 회원id로 찾자
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("User " + username );   //참조
    }

    public User findByNum(Long userNum) throws UsernameNotFoundException {
        return userRepository.findByUserNum(userNum);
    }
}