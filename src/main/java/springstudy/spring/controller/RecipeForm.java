package springstudy.spring.controller;

import lombok.Getter;
import lombok.Setter;
import springstudy.spring.domain.RecipePhoto;

@Getter @Setter
public class RecipeForm {

    private String name;
    private String recipe_content;
    private Integer recipe_date;
    private RecipePhoto image;
}
