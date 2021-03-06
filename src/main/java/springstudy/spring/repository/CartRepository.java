package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.Cart;
import springstudy.spring.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final EntityManager em;

    public void save(Cart cart) {
        em.persist(cart);
    }

    public Cart findOne(Long id) { // cart_id로 카트 찾아오는 함수
        return em.find(Cart.class, id);
    }

    public List<Cart> findAll(User user){
        return em.createQuery("select c from Cart c where c.user = :userNum",
                Cart.class)
                .setParameter("userNum", user)
                .getResultList();
    }

    public void delete(Long id){
        Cart cart = findOne(id);
        em.remove(cart);
    }
}

