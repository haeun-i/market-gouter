package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.ItemOption;
import springstudy.spring.domain.ItemPhoto;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemOptionRepository{
    private final EntityManager em;

    public void save(ItemOption itemOption){
        em.persist(itemOption);
    }

    public ItemOption findOne(Long id){
        return em.find(ItemOption.class, id);
    }

    public List<ItemOption> findAll(Long id) {
        return em.createQuery("select i from ItemOption i where i.id = :id", ItemOption.class)
                .setParameter("id",id)
                .getResultList();
    }

    public void delete(Long id){
        ItemOption itemOption = findOne(id);
        em.remove(itemOption);
    }


}
