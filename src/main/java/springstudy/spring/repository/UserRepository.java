package springstudy.spring.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository //자동으로 관리대상인 스프링 빈으로 등록
@RequiredArgsConstructor
public class UserRepository {


    private final EntityManager em;

    //*구현 기능*
    //회원가입(회원 등록, 회원 조회, 회원 수정, 회원 삭제)

    //회원 등록
    public void save(User user){
        em.persist(user); //실제 DB 저장x, 영속성 컨텍스트 저장
    }

    //회원id로 단건 조회
    public User findOne(Long userId){
        return em.find(User.class, userId);
    }


    //아이디 중복확인 -> 서비스에서 이용할 것
    public List<User> findById(Long userId){
        return em.createQuery("select m " +
                        " from User m " +
                "where m.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();

    }


}

