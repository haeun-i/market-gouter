package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongBinaryOperator;

@Entity
@Getter @Setter
@Table(name = "Recipe")
public class Recipe {
    @Id @GeneratedValue
    @Column(name = "recipe_id")       // recipe table의 타입이 없어 구분을 위함

    private Long id;
    private String name;     // 레시피 제목
    private String recipe_content;
    private Integer recipe_date;
    @Enumerated(EnumType.STRING)    // .origin이 기본, string으로 넣어야 나중에 오류 방지
    private RecipeStatus status; // recipe, cancel

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe")       // 레시피-카테고리 다대일관계
    private CategoryRecipe category_recipe;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // 레시피 - 유저 다대일
    @JoinColumn(name = "user_num")
    private User user;              // user 받아와야함

    //private String recipe_image;  //타입 재설정 필요
    @OneToMany(
            mappedBy = "recipe",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<RecipePhoto> photo = new ArrayList<>();

    //연관관계 매서드
    public void setCategoryRecipe(CategoryRecipe categoryRecipe){
        //this.category_recipe = categoryRecipe;
        //categoryRecipe.setRecipe(this);
    }

    public void setMember(User user){
        this.user = user;
        user.getRecipes().add(this);

    }
    public void addComment(Comment comment){
        commentList.add(comment);
        comment.setRecipe(this);
    }

    // recipe photo 파일 처리 위함
    public void addPhoto(RecipePhoto photo) {
        this.photo.add(photo);

        // 게시글에 파일이 저장되어있지 않은 경우
        if(photo.getRecipe() != this)
            // 파일 저장
            photo.setRecipe(this);
    }

    //생성 매서드     레시피 글 작성
    public static Recipe CreateRecipe(String content, int date, CategoryRecipe category, User user){
        Recipe recipe = new Recipe();
        recipe.setRecipe_content(content);
        recipe.setRecipe_date(date);
        recipe.setCategoryRecipe(category);
        recipe.setUser(user);
        recipe.setStatus(RecipeStatus.RECIPE);
        return recipe;
    }

    public void cancel(){
        this.setStatus(RecipeStatus.CANCEL);
    }

}
