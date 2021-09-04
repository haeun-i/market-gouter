package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.CategoryItem;
import springstudy.spring.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryItemRepository {
    private final EntityManager em;

    // 카테고리 명에는 별도의 검색 메소드가 필요하다고 생각하지 않아 추가X
    public Long save(CategoryItem categoryItem){
        if(categoryItem.getId() == null){
            em.persist(categoryItem);
        } else{
            em.merge(categoryItem);
        }
        return categoryItem.getId();
    }

    public List<CategoryItem> findAll(){
        return em.createQuery("select i from CategoryItem i", CategoryItem.class)
                .getResultList();
    }

    public CategoryItem findOne(Long id){
        return em.find(CategoryItem.class, id);
    }
}
