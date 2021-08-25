package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "category_id")
public class CategoryRecipe {
    @Id @GeneratedValue
    @Column(name = "category_id")    // 객체 필드와 DB 테이블 컬럼 맵핑
    private Long id;
    @OneToMany(mappedBy = "category_id")
    @JoinColumn(name = "recipe_id")
    private List<Recipe> recipe = new ArrayList<>();
    private String category_name;
}