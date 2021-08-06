package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongBinaryOperator;

@Entity
@Getter @Setter
public class Recipe {
    @Id @GeneratedValue
    @Column(name = "recipe_id")       // recipe table의 타입이 없어 구분을 위함

    private Long id;
    private String recipe_content;
    private Integer recipe_date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")       // 레시피-카테고리 다대일관계
    private CategoryRecipe recipe_category;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 레시피 - 유저 다대일
    @JoinColumn(name = "user_num")
    private User user;              // user 받아와야함

    private String recipe_image;  //타입 재설정 필요
    private Integer view_num;
}
