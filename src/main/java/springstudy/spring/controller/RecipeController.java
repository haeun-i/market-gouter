package springstudy.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springstudy.spring.domain.CategoryRecipe;
import springstudy.spring.domain.Recipe;
import springstudy.spring.service.RecipeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(value = "/recipesAll")     // 레시피 전체 조회
    public String recipeList(Model model){
        List<Recipe> recipes = recipeService.findRecipe();
        model.addAttribute("recipes", recipes);
        return "recipes/recipeList";
    }

    @GetMapping(value = "/recipesCategory")   // 카테고리별 조회
    public String recipeCategory(Model model, @RequestParam("categoryRecipe") CategoryRecipe categoryRecipe){
        List<Recipe> category = recipeService.findRecipeByCategory(categoryRecipe);
        model.addAttribute("recipes", category);
        return "recipes/categories";
    }

    @PostMapping("/recipes")          // 레시피 작성
    public String createForm(Model model){
        model.addAttribute("recipeForm", new RecipeForm());
        return "redirect:/recipes";
    }

    @GetMapping("/recipe")        // 레시피 검색
    public String searchRecipe(@RequestParam("recipeId") Long recipeId){
        recipeService.findone(recipeId);
        return "recipes/details";
    }

    @PostMapping(value = "/recipes/cancel")     // 레시피 삭제
    public String cancelRecipe(@RequestParam("recipeId") Long recipeId){
        recipeService.cancelRecipe(recipeId);
        return "redirect:/recipes";
    }
}