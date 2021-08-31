package springstudy.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.spring.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
    User findByUserNum(Long userNum);

}

