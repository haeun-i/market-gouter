package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(Long usernum){ // 유저의 카트 전체조회
        return em.createQuery("select o from Order o where o.user_num = :user_num",
                Order.class)
                .setParameter("user_num", usernum)
                .getResultList();
    }

    public void delete(Order order){
        em.remove(order);
    }
}
