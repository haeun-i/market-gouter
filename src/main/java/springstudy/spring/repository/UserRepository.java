package springstudy.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.spring.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
    User findByUserNum(Long userNum);

}

