package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.Item;
import springstudy.spring.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
        private final EntityManager em;

        public Long save(Item item){
            em.persist(item);
            return item.getId();
        }

        public Item findOne(Long id){
            return em.find(Item.class, id);
        }

        public List<Item> findAll(){
            return em.createQuery("select i from Item i", Item.class)
                    .getResultList();
        }

        public List<Item> findByName(String name){
            return em.createQuery("select i from Item i where i.name = :name", Item.class)
                    .setParameter("name", name)
                    .getResultList();
        }


}
