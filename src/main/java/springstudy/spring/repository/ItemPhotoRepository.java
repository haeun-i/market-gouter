package springstudy.spring.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.ItemPhoto;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemPhotoRepository {
    private final EntityManager em;

    public void save(ItemPhoto itemPhoto){
        em.persist(itemPhoto);
    }

    public ItemPhoto findOne(Long id){
        return em.find(ItemPhoto.class, id);
    }

    public List<ItemPhoto> findAll(Long id) {
        return em.createQuery("select i from ItemPhoto i where i.item_photo_id = :item_photo_id", ItemPhoto.class)
                .setParameter("item_photo_id",id)
                .getResultList();
    }

    public void delete(Long id){
        ItemPhoto itemPhoto = findOne(id);
        em.remove(itemPhoto);
    }
}
