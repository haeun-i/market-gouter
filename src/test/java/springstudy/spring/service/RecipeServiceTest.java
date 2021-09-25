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
    @Autowired
    UserService userService;
    @Test
    public void 레시피추가() throws Exception{
        //given
        User user = userService.findByNum(2L);

        CategoryRecipe categoryRecipe = new CategoryRecipe();
        categoryRecipe.setCategory_name("meat");
        categoryRecipe.setId(100L);

        //CreateRecipe(String content, int date, CategoryRecipe category, User user
        String content = "recipe contents!";
        int date = 26;

        // when
        // public Long join(Long userNum, String content, int date, CategoryRecipe category
        Long recipeId = recipeService.join(user.getUserNum(), content, date, categoryRecipe);

        // then
        Recipe recipe1 = recipeRepository.findById(recipeId);    // setID 부분 채워야함

        assertEquals(RecipeStatus.RECIPE, recipe1.getStatus()); // 기댓값, 실제 상태가 같냐?
        System.out.println("recipe의 카테고리는");
        System.out.println(recipe1.getCategory_recipe());
    }

    @Test
    public void 레시피삭제() throws Exception{

        //레시피 아이디가 20인 것 생성 후 삭제 확인 가능
        Recipe getRecipe = recipeRepository.findById(20L);
        Long recipeID = getRecipe.getId();

        recipeService.cancelRecipe(recipeID);
        // 상태가 cancel로 동일해야함
        assertEquals(RecipeStatus.CANCEL, getRecipe.getStatus());

    }


}