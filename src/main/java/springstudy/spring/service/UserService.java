package springstudy.spring.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springstudy.spring.domain.User;
import springstudy.spring.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
