package springstudy.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springstudy.spring.domain.CategoryRecipe;
import springstudy.spring.domain.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository   // 스프링 빈에서 관리하게 됨
@RequiredArgsConstructor
public class RecipeRepository {

   // @PersistenceContext   // 스프링이 em 만들어서 주입해줌
    private final EntityManager em;

//    public void save(Recipe recipe){   // 레시피 작성
//        em.persist(recipe);
//    }

    public void save(Recipe recipe){    // 레시피 작성
        if (recipe.getId() == null){
            em.persist(recipe);
        }else{
            em.merge(recipe);
        }
    }

    public Recipe findRecipe(Long id){     // 레시피 검색
        return em.find(Recipe.class, id);
    }

    public List<Recipe> findAll(){     // 레시피 전체 조회
        List<Recipe> result = em.createQuery("select m from Recipe m", Recipe.class)
                .getResultList();
        return result;
    }

    public List<Recipe> findByCategory(CategoryRecipe category){   // 카테고리에 의한 검색
        return em.createQuery("select m from Recipe m where m.recipe_category = :recipe_catagory", Recipe.class)
                .setParameter("recipe_catagory", category)
                .getResultList();
    }


}
