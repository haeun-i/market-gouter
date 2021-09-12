package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.Payment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
    private final EntityManager em;

    public void save(Payment payment) {
        em.persist(payment);
    }

    public Payment findOne(Long id) { // cart_id로 카트 찾아오는 함수
        return em.find(Payment.class, id);
    }


    public void delete(Long id){
        Payment payment = findOne(id);
        em.remove(payment);
    }
}

