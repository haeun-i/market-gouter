package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.function.LongBinaryOperator;

@Entity
@Getter @Setter
public class Recipe {
    @Id @GeneratedValue
    @Column(name = "recipe_id")       // recipe table의 타입이 없어 구분을 위함

    private Integer recipe_id;
    private String recipe_content;
    private Integer recipe_date;
    @Embedded
    @ManyToOne
    @JoinColumn(name = "category_id")       // 레시피-카테고리 다대일관계
    private CategoryRecipe recipe_category;
    private Integer comment_num;
    @Embedded
    @ManyToOne         // 레시피 - 유저 다대일
    @JoinColumn(name = "user_id")
    private User user_id;              // user 받아와야함
    private String recipe_image;  //타입 재설정 필요
    private Integer view_num;
}
