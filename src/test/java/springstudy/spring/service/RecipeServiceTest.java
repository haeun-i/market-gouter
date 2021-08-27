package springstudy.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.CategoryRecipe;
import springstudy.spring.domain.Recipe;
import springstudy.spring.domain.RecipeStatus;
import springstudy.spring.domain.User;
import springstudy.spring.repository.RecipeRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipeServiceTest {

    @Autowired EntityManager em;
    @Autowired RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Test
    public void 레시피추가() throws Exception{
        User user = new User();
        //user.setUserId();
        em.persist(user);

        CategoryRecipe categoryRecipe = new CategoryRecipe();
        categoryRecipe.setCategory_name("meat");
        em.persist(categoryRecipe);

        Recipe recipe = new Recipe();
        recipe.setId(10000L);
        recipe.setName("sausage");
        recipe.setRecipe_content("소세지 야채 볶음은 이렇다!");
        recipe.setRecipe_date(826);
        recipe.setCategoryRecipe(categoryRecipe);
        recipe.setUser(user);
        em.persist(recipe);

        // when
        Long id = 1000L;       // 유저 아이디로 세팅해야함
        Long recipeId = recipeService.join(id);

        // then
        Recipe recipe1 = recipeRepository.findById(recipeId);    // setID 부분 채워야함

        assertEquals(RecipeStatus.RECIPE, recipe1.getStatus()); // 기댓값, 실제 상태가 같냐?
    }

    @Test      // 먼저 주문을 하고 취소를 해얗ㅁ
    public void 레시피삭제() throws Exception{
        User user = new User();
        //user.setUserId();

        CategoryRecipe categoryRecipe = new CategoryRecipe();
        categoryRecipe.setCategory_name("meat");

        Recipe recipe = new Recipe();
        recipe.setId(10000L);
        recipe.setName("sausage");
        recipe.setRecipe_content("소세지 야채 볶음은 이렇다!");
        recipe.setRecipe_date(826);
        recipe.setCategoryRecipe(categoryRecipe);
        recipe.setUser(user);

        Long id = 1000L;       // 유저 아이디로 세팅해야함
        Long recipeId = recipeService.join(id);
        //when
        recipeService.cancelRecipe(recipeId);

        Recipe recipe1 = recipeRepository.findById(recipeId);

        assertEquals(RecipeStatus.CANCEL, recipe1.getStatus());

    }


}