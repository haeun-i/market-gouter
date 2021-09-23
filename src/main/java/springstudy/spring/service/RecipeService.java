package springstudy.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.spring.domain.CategoryRecipe;
import springstudy.spring.domain.Recipe;
import springstudy.spring.domain.User;
import springstudy.spring.repository.RecipeRepository;
import springstudy.spring.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)  // 읽기 전용 - default
@RequiredArgsConstructor
public class RecipeService {

//    @Autowired   // 필드 주입, setter injection 등으로 바꿀수도 있다.
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    //Autowired 대신 생성자 주입
    // 필요한 기능들
    // 레시피 작성 - 중복 상관 x but id값은 중복되면 안됨 - 생성시에 id값 1씩 증가하도록?
    // requiredArgsConstructor가 이 역할을 해줌
    //    public RecipeService(RecipeRepository recipeRepository){
    //        this.recipeRepository = recipeRepository;
    //    }
    @Transactional  // 읽기전용이 아닌 쓰기도 허용하므로
    public Long join(Long userNum, String content, int date, CategoryRecipe category){
        // 유저 정보 추가 해야함
        // 유저 id find로 찾아야함
        User user = userRepository.findByUserNum(userNum);

        // 레시피 상태
        Recipe recipe = Recipe.CreateRecipe(content, date, category, user);   // content, date, cate, user
        //String content, int date, CategoryRecipe category, User user
        recipeRepository.save(recipe);

        return recipe.getId();
    }

    // 레시피 조회(전체)
    public List<Recipe> findRecipe(){
        return recipeRepository.findAll();
    }

    //레시피 조회(카테고리별)
    public List<Recipe> findRecipeByCategory(CategoryRecipe categoryRecipe){
        return recipeRepository.findByCategory(categoryRecipe);
    }

    // 레시피 검색(상세) - 하나만 검색
    public Recipe findOne(Long recipeID){
        return recipeRepository.findById(recipeID);
    }

    @Transactional  // 데이터 변경하므로 readonly = false
    public void cancelRecipe(Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId);
        recipe.cancel();
    }

}
