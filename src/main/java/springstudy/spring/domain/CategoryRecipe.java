package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class CategoryRecipe {

    private Integer category_id;
    private String category_name;
}
